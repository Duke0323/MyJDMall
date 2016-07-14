package io.github.duke0323.myjdmall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.CategoryBean;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class TopCategoryAdapter extends JDBaseAdapter<CategoryBean> {
    public int mTabPosition=-1;
    Context mContext;
    private final LayoutInflater mInflater;

    public TopCategoryAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    class viewHolder {
        View divider;
        TextView tv;
        RelativeLayout rl;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder=null;
        if (convertView != null) {
            holder = (viewHolder) convertView.getTag();
        } else {
            holder = new viewHolder();
            convertView = mInflater.inflate(R.layout.top_category_item, null);
            holder.divider = convertView.findViewById(R.id.divider);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            holder.rl = (RelativeLayout) convertView.findViewById(R.id.rl);
            convertView.setTag(holder);
        }
        CategoryBean categoryBean = mDatas.get(position);
        holder.tv.setText(categoryBean.getName());
        holder.divider.setVisibility((mTabPosition == position) ? View.INVISIBLE : View.VISIBLE);
if(mTabPosition == position) {
    holder.tv.setBackgroundResource(R.drawable.tongcheng_all_bg01);
}else {
    holder.tv.setBackgroundColor(Color.WHITE);
}

        return convertView;
    }

}
