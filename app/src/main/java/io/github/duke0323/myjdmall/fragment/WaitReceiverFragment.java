package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.adapter.WaitReceiveAdapter;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IConfirmOrderListener;
import io.github.duke0323.myjdmall.ui.XListView;

/**
 * Created by ${Duke} on 2016/7/21.
 */
public class WaitReceiverFragment extends OrderFragment implements IConfirmOrderListener {

    private ImageView null_image;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_wait_receive, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHandler();
        initController();
        initView();
        mController.sendAsyncMessage(IDiyMessage.GET_ORDER_ACTION, 2);
    }

    @Override
    protected void initView() {
        mXListView = (XListView) getActivity().findViewById(R.id.wait_receive_order_lv);
        null_image = (ImageView) getActivity().findViewById(R.id.wait_receive_null_view).findViewById(R.id.null_image);
        mXListView.setPullRefreshEnable(true);
        mXListView.setPullLoadEnable(true);
        mXListView.setXListViewListener(this);
        mAdapter = new WaitReceiveAdapter(getActivity());
        mXListView.setAdapter(mAdapter);
        ((WaitReceiveAdapter) mAdapter).setListener(this);
    }

    @Override
    public void onRefresh() {
        mController.sendAsyncMessage(IDiyMessage.GET_ORDER_ACTION, 2);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onConfirmOrder(String oid) {
        mController.sendAsyncMessage(IDiyMessage.CONFIRM_ORDER_ACTION,oid);
    }
}
