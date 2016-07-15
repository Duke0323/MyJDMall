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
import io.github.duke0323.myjdmall.bean.ProductListBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * 食品饮料糖果巧克力有数据
 * Created by ${Duke} on 2016/7/15.
 */
public class ProductListAdapter extends JDBaseAdapter<ProductListBean> {
    Context mContext;

    public ProductListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_lv_item, null);
        }
        ImageView product = ViewHolder.get(convertView, R.id.product_iv);
        TextView name = ViewHolder.get(convertView, R.id.name_tv);
        TextView commrate = ViewHolder.get(convertView, R.id.commrate_tv);
        TextView price = ViewHolder.get(convertView, R.id.price_tv);
        ImageView shopcar = ViewHolder.get(convertView, R.id.shopcar_iv);
        ProductListBean productListBean = mDatas.get(position);
        Picasso.with(mContext).load(HttpConst.DOMAIN + productListBean.getIconUrl())
                .config(Bitmap.Config.RGB_565).into(product);
        name.setText(productListBean.getName());
        commrate.setText(String.valueOf(productListBean.getCommentCount() + "条评价  好评率" + productListBean.getFavcomRate() + "%"));
        price.setText(String.valueOf("￥" + productListBean.getPrice()));

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
