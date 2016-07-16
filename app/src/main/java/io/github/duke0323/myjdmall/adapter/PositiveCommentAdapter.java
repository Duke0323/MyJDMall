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
import io.github.duke0323.myjdmall.bean.CommentBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.ui.RatingBar;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * Created by ${Duke} on 2016/7/15.
 */
public class PositiveCommentAdapter extends JDBaseAdapter<CommentBean> {
    Context mContext;

    public PositiveCommentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_good_comment_item_layout, null);
        }
        RatingBar ratingBar = ViewHolder.get(convertView, R.id.rating_bar);
        TextView name = ViewHolder.get(convertView, R.id.name_tv);
        TextView content = ViewHolder.get(convertView, R.id.content_tv);
        LinearLayout imagesContainer = ViewHolder.get(convertView, R.id.images_container);
        CommentBean commentBean = mDatas.get(position);

        name.setText("用户:" + commentBean.getUserName());
        content.setText(commentBean.getComment());
        ratingBar.setRating(commentBean.getRate());
        initCommentImage(imagesContainer, commentBean.getImgUrls());
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
