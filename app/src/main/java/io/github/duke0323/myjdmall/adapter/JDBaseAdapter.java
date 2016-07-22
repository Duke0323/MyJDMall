package io.github.duke0323.myjdmall.adapter;

import android.widget.BaseAdapter;

import java.util.List;

import io.github.duke0323.myjdmall.fragment.WaitReceiverFragment;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public abstract  class JDBaseAdapter<T> extends BaseAdapter {
   protected List<T> mDatas;

    public void setDatas(List<T> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
