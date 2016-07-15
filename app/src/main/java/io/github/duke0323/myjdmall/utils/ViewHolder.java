package io.github.duke0323.myjdmall.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * viewholder帮助类
 * Created by ${Duke} on 2016/7/15.
 */
public class ViewHolder {
    public static <T extends View> T get(View view, int id) {
        //性能优化：使用SparseArray代替HashMap<Integer,Object>
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
