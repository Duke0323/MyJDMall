package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.OrderStatusBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.protocol.IConfirmOrderListener;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * 食品饮料糖果巧克力有数据
 * Created by ${Duke} on 2016/7/15.
 */
public class WaitReceiveAdapter extends JDBaseAdapter<OrderStatusBean> {
    Context mContext;
    private String mTn;
    private IConfirmOrderListener mListener;


    public WaitReceiveAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, null);
        }
        TextView order_no_tv = ViewHolder.get(convertView, R.id.order_no_tv);
        TextView order_state_tv = ViewHolder.get(convertView, R.id.order_state_tv);
        LinearLayout p_container_ll = ViewHolder.get(convertView, R.id.p_container_ll);
        TextView price_tv = ViewHolder.get(convertView, R.id.price_tv);
        Button do_btn = ViewHolder.get(convertView, R.id.do_btn);
        final OrderStatusBean orderStatusBean = mDatas.get(position);

        //显示图片
        int childCount = p_container_ll.getChildCount();
        String items = orderStatusBean.getItems();
        List<String> imgs = JSON.parseArray(items, String.class);
        int size = imgs.size();
        int count = Math.min(childCount, size);
        for (int i = 0; i < count; i++) {
            ImageView imageView = (ImageView) p_container_ll.getChildAt(i);
            imageView.setVisibility(View.INVISIBLE);
            Picasso.with(mContext).load(HttpConst.DOMAIN + imgs.get(i)).config(Bitmap.Config.RGB_565)
                    .into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
        //显示i订单编号
        order_no_tv.setText("订单编号" + orderStatusBean.getOrderNum());
        //显示订单状态
        String status = "";
        //-1取消订单 0待支付 1待发货 2 待收货3 完成交易
        switch (orderStatusBean.getStatus()) {
            case -1:
                status = "已取消";
                break;
            case 0:
                status = "待支付";

                break;
            case 1:
                status = "待发货";
                break;
            case 2:
                status = "待收货";
                do_btn.setVisibility(View.VISIBLE);
                do_btn.setText("确认收货");
                do_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long oid = orderStatusBean.getOid();
                        mListener.onConfirmOrder(String.valueOf(oid));
                    }
                });
                break;
            case 3:
                status = "已完成";
                break;
        }
        order_state_tv.setText(status);
        price_tv.setText(String.valueOf("￥" + orderStatusBean.getTotalPrice()));

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }

    public void setListener(IConfirmOrderListener listener) {
        mListener = listener;
    }
}
