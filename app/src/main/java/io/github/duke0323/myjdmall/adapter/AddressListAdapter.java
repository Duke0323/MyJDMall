package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.AddressBean;
import io.github.duke0323.myjdmall.protocol.IDeleteAddressChangeListener;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * 食品饮料糖果巧克力有数据
 * Created by ${Duke} on 2016/7/15.
 */
public class AddressListAdapter extends JDBaseAdapter<AddressBean> {
    Context mContext;
    private IDeleteAddressChangeListener mListener;

    public AddressListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.choose_address_item_layout, null);
        }
        RelativeLayout hasRecevier = ViewHolder.get(convertView, R.id.has_receiver_rl);
        ImageView isDeafult = ViewHolder.get(convertView, R.id.isDeafult_iv);
        TextView name = ViewHolder.get(convertView, R.id.name_tv);
        TextView phone = ViewHolder.get(convertView, R.id.phone_tv);
        TextView address = ViewHolder.get(convertView, R.id.address_tv);
        TextView delete = ViewHolder.get(convertView, R.id.delete_tv);
        final AddressBean addressBean = mDatas.get(position);
        name.setText(addressBean.getReceiverName());
        phone.setText(addressBean.getReceiverPhone());
        address.setText(addressBean.getReceiverAddress());
        isDeafult.setVisibility(addressBean.isDefault() ? View.VISIBLE : View.INVISIBLE);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) {

                    mListener.onDelete(addressBean.getId());
                }
            }
        });

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }

    public void setListener(IDeleteAddressChangeListener listener) {
        mListener = listener;
    }
}
