package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.SecKillBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class SecKillAdapter extends JDBaseAdapter<SecKillBean> {
    Context mContext;

    public SecKillAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_seckill_item, null);
        }
        ImageView image_iv = ViewHolder.get(convertView, R.id.image_iv);
        TextView nowprice_tv = ViewHolder.get(convertView, R.id.nowprice_tv);
        TextView normalprice_tv = ViewHolder.get(convertView, R.id.normalprice_tv);
        SecKillBean secKillBean = mDatas.get(position);
        nowprice_tv.setText("￥" + String.valueOf(secKillBean.getPointPrice()));
        normalprice_tv.setText("￥" + String.valueOf(secKillBean.getAllPrice()));
        normalprice_tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        Picasso.with(mContext).load(HttpConst.DOMAIN + secKillBean.getIconUrl())
                .config(Bitmap.Config.RGB_565).into(image_iv);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getProductId() : 0;
    }
}
