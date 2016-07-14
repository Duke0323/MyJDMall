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

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class SecKillAdapter extends JDBaseAdapter<SecKillBean> {
    class viewHoler {
        ImageView image_iv;
        TextView nowprice_tv;
        TextView normalprice_tv;
    }

    Context mContext;

    public SecKillAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHoler holer = null;
        if (convertView != null) {
            holer = (viewHoler) convertView.getTag();
        } else {
            holer = new viewHoler();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_seckill_item, null);
            holer.image_iv = (ImageView) convertView.findViewById(R.id.image_iv);
            holer.nowprice_tv = (TextView) convertView.findViewById(R.id.nowprice_tv);
            holer.normalprice_tv = (TextView) convertView.findViewById(R.id.normalprice_tv);
            convertView.setTag(holer);
        }
        SecKillBean secKillBean = mDatas.get(position);
        holer.nowprice_tv.setText("￥" + String.valueOf(secKillBean.getPointPrice()));
        holer.normalprice_tv.setText("￥" + String.valueOf(secKillBean.getAllPrice()));
        holer.normalprice_tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        Picasso.with(mContext).load(HttpConst.DOMAIN + secKillBean.getIconUrl())
                .config(Bitmap.Config.RGB_565).into(holer.image_iv);
        return convertView;
    }
}
