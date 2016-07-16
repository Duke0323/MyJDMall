package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.utils.ViewHolder;

/**
 * Created by ${Duke} on 2016/7/16.
 */
public class ProductVersionAdapter extends JDBaseAdapter<String>{
    public int mTabPosition = -1;
    Context mContext;
    private final LayoutInflater mInflater;


    public ProductVersionAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.brand_gv_item, parent, false);
        }
        //使用viewholder帮助类
        Button brandBtn = ViewHolder.get(convertView, R.id.brand_tv);
        String s = mDatas.get(position);
        brandBtn.setText(s);
        brandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabPosition = position;
                notifyDataSetChanged();
            }
        });
        brandBtn.setSelected(mTabPosition == position);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }


}
