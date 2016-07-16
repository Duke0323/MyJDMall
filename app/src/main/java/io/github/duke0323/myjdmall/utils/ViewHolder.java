package io.github.duke0323.myjdmall.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * viewholder帮助类
 * Created by ${Duke} on 2016/7/15.
 */
public class ViewHolder {
    public static <T extends View> T get(View view, int id) {
        /*性能优化：使用SparseArray代替HashMap<Integer,Object>
        使用key为Integer的HashMap，就会出现黄色警告，
        提示使用SparseArray，SparseArray具有比HashMap更高的内存使用效率，
        我们在前面的《Android HashMap源码详解》中提到，
        http://blog.csdn.net/abcdef314159/article/details/51679494
        HashMap的存储方式是数组加链表，今天要分析的SparseArray是使用纯数组的形式存储。*/
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
