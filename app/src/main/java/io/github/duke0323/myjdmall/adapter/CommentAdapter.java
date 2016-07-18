package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.CommentDetailBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.ui.RatingBar;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * 食品饮料糖果巧克力有数据
 * Created by ${Duke} on 2016/7/15.
 */
public class CommentAdapter extends JDBaseAdapter<CommentDetailBean> {
    Context mContext;

    public CommentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_item_layout, null);
        }
        ImageView mUserIconIv = ViewHolder.get(convertView, R.id.icon_iv);
        TextView mUserNameTv = ViewHolder.get(convertView, R.id.name_tv);
        TextView mUserCommentTimeTv = ViewHolder.get(convertView, R.id.time_tv);
        RatingBar mCommentRatingBar = ViewHolder.get(convertView, R.id.rating_bar);
        TextView mUserCommentTv = ViewHolder.get(convertView, R.id.content_tv);
        LinearLayout mImageContainerLl = ViewHolder.get(convertView, R.id.images_container);
        TextView mBuyTimeTv = ViewHolder.get(convertView, R.id.buytime_tv);
        TextView mProductVersionTv = ViewHolder.get(convertView, R.id.buyversion_tv);
        TextView mLoveCountTv = ViewHolder.get(convertView, R.id.lovecount_tv);
        TextView mSubCommentTv = ViewHolder.get(convertView, R.id.subcomment_tv);
        CommentDetailBean commentDetailBean = mDatas.get(position);

        Picasso.with(mContext).load(HttpConst.DOMAIN + commentDetailBean.getUserImg())
                .config(Bitmap.Config.RGB_565).into(mUserIconIv);
        mUserNameTv.setText(commentDetailBean.getUserName());
        mUserCommentTimeTv.setText(commentDetailBean.getCommentTime());
        mCommentRatingBar.setRating(commentDetailBean.getRate());
        mUserCommentTv.setText(commentDetailBean.getComment());

        initCommentImage(mImageContainerLl, commentDetailBean.getImgUrls());

        mBuyTimeTv.setText(commentDetailBean.getBuyTime());
        mProductVersionTv.setText(commentDetailBean.getProductType());
        mLoveCountTv.setText(String.valueOf(commentDetailBean.getLoveCount()));
        mSubCommentTv.setText(String.valueOf(commentDetailBean.getSubComment()));


        return convertView;
    }
    private void initCommentImage(LinearLayout imagesContainer, String imgUrls) {
        List<String> imageUrls = JSON.parseArray(imgUrls, String.class);
        //linearlayout4个图片
        int childCount = imagesContainer.getChildCount();
        //隐藏
        for (int i = 0; i < childCount; i++) {
            imagesContainer.getChildAt(i).setVisibility(View.INVISIBLE);
        }
        int size = imageUrls.size();
        int count = childCount > size ? size : childCount;
        //复用时注意
        //每次显示之前清除掉原来图片显示的内存
        for (int i = 0; i < count; i++) {
            ImageView imageView = (ImageView) imagesContainer.getChildAt(i);
            Picasso.with(mContext).load(HttpConst.DOMAIN + imageUrls.get(i))
                    .config(Bitmap.Config.RGB_565).into(imageView);
            imagesContainer.getChildAt(i).setVisibility(View.VISIBLE);
        }
        //复用时发现没有就要gone掉
        imagesContainer.setVisibility(imageUrls.size() > 0 ? View.VISIBLE : View.GONE);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
