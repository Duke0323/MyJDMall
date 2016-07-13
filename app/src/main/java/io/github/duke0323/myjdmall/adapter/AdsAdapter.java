package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.bean.AdsResultBean;
import io.github.duke0323.myjdmall.config.HttpConst;

/**
 * Created by ${Duke} on 2016/7/12.
 */
public class AdsAdapter extends PagerAdapter {

    List<ImageView> imgs;
    List<AdsResultBean> datas;
    Context mContext;

//    public AdsAdapter() {
//
//    }

    public AdsAdapter(List<ImageView> imgs, List<AdsResultBean> datas, Context context) {
        this.imgs = imgs;
        this.datas = datas;
        this.mContext = context;
    }

    public void setDatas(List<AdsResultBean> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
        imgs = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT));
            String adUrl = datas.get(i).getAdUrl();
            Picasso.with(mContext).load(HttpConst.DOMAIN + adUrl)
                    .config(Bitmap.Config.RGB_565).into(imageView);
            imgs.add(imageView);

        }
    }

    @Override
    public int getCount() {
        return datas!=null?Integer.MAX_VALUE:0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imgs.get(position % datas.size());
        String adUrl = datas.get(position% datas.size()).getAdUrl();
        Picasso.with(mContext).load(HttpConst.DOMAIN + adUrl)
                .config(Bitmap.Config.RGB_565).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position % datas.size()));
    }
}
