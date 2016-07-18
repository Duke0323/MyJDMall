package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.ShopListBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.protocol.IShopCarDeleteListener;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class ShopCarAdapter extends JDBaseAdapter<ShopListBean> {
    //记录当前选中的值
    private ArrayList<Boolean> mSeletedItems = new ArrayList<Boolean>();
    Context mContext;
    private final LayoutInflater mInflater;
    private IShopCarDeleteListener mDeleteListener;

    public ShopCarAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    public void setItemSeleted(int position) {
        //正反选
        mSeletedItems.set(position, !mSeletedItems.get(position));
    }

    @Override
    public void setDatas(List<ShopListBean> datas) {
        super.setDatas(datas);
        mSeletedItems.clear();
        //默认不选中
        for (int i = 0; i < datas.size(); i++) {
            mSeletedItems.add(false);
        }
    }

    //设置全选
    public void setSelectedAllItems(boolean isChecked) {
        for (int i = 0; i < mSeletedItems.size(); i++) {
            mSeletedItems.set(i, isChecked);
        }

    }

    //选中后传递给外界 选中的个数
    public int getSelectedCount() {
        int result = 0;
        for (int i = 0; i < mSeletedItems.size(); i++) {
            if (mSeletedItems.get(i)) {
                result++;
            }
        }
        return result;
    }

    //计算选中的金额
    public double getSelectedTotalPrice() {
        double result = 0;
        for (int i = 0; i < mSeletedItems.size(); i++) {
            if (mSeletedItems.get(i)) {
                //计算
                ShopListBean shopListBean = mDatas.get(i);
                result += shopListBean.getPprice() * shopListBean.getBuyCount();
            }
        }
        return result;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.shopcar_lv_item, parent, false);
        }
        //使用viewholder帮助类
        CheckBox cbx = ViewHolder.get(convertView, R.id.cbx);
        ImageView product = ViewHolder.get(convertView, R.id.product_iv);
        TextView name = ViewHolder.get(convertView, R.id.name_tv);
        TextView version = ViewHolder.get(convertView, R.id.version_tv);
        TextView privice = ViewHolder.get(convertView, R.id.price_tv);
        TextView buyCount = ViewHolder.get(convertView, R.id.buyCount_tv);
        Button delete = ViewHolder.get(convertView, R.id.delete_product);
        final ShopListBean shopListBean = mDatas.get(position);
        cbx.setChecked(mSeletedItems.get(position));
        Picasso.with(mContext).load(HttpConst.DOMAIN + shopListBean.getPimageUrl())
                .config(Bitmap.Config.RGB_565).into(product);
        name.setText(shopListBean.getPname());
        version.setText(shopListBean.getPversion());
        privice.setText(String.valueOf("￥" + shopListBean.getPprice()));
        buyCount.setText(String.valueOf(shopListBean.getBuyCount()));
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDeleteListener!=null) {

                    mDeleteListener.onDeleteItem(shopListBean.getId());
                }
            }
        });

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }

    public void setDeleteListener(IShopCarDeleteListener deleteListener) {
        mDeleteListener = deleteListener;
    }
}
