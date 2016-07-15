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
import io.github.duke0323.myjdmall.utils.ViewHolder;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            convertView = mInflater.inflate(R.layout.recommend_gv_item, parent,false);
        }
        ImageView image_iv= ViewHolder.get(convertView,R.id.image_iv);
        TextView name_tv=  ViewHolder.get(convertView,R.id.name_tv);
        TextView price_tv= ViewHolder.get(convertView,R.id.price_tv);

        RecoBean recoBean = mDatas.get(position);
        name_tv.setText(recoBean.getName());
        price_tv.setText("￥" +String.valueOf(recoBean.getPrice()));
        Picasso.with(mContext).load(HttpConst.DOMAIN + recoBean.getIconUrl())
                .config(Bitmap.Config.RGB_565).into(image_iv);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return mDatas!=null?mDatas.get(position).getProductId():0;
    }
}
