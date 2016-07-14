package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.RecoBean;
import io.github.duke0323.myjdmall.config.HttpConst;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class RecommendAdapter extends JDBaseAdapter<RecoBean> {
    Context mContext;
    private final LayoutInflater mInflater;


    public RecommendAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    class viewHolder {
        ImageView image_iv;
        TextView name_tv;
        TextView price_tv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView != null) {
            holder = (viewHolder) convertView.getTag();
        } else {
            holder = new viewHolder();
            convertView = mInflater.inflate(R.layout.recommend_gv_item, parent,false);
            holder.image_iv = (ImageView) convertView.findViewById(R.id.image_iv);
            holder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.price_tv = (TextView) convertView.findViewById(R.id.price_tv);
            convertView.setTag(holder);
        }
        RecoBean recoBean = mDatas.get(position);
        holder.name_tv.setText(recoBean.getName());
        holder.price_tv.setText("￥" +String.valueOf(recoBean.getPrice()));
        Picasso.with(mContext).load(HttpConst.DOMAIN + recoBean.getIconUrl())
                .config(Bitmap.Config.RGB_565).into(holder.image_iv);
        return convertView;
    }
}
