package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.OrderController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.adapter.AllOrderAdapter;
import io.github.duke0323.myjdmall.bean.OrderStatusBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.ui.XListView;

/**
 * Created by ${Duke} on 2016/7/21.
 */
public class AllOrderListFragment extends BaseFragemnt implements IModelChangeListener, XListView.IXListViewListener {
    private XListView mXListView;
    private AllOrderAdapter mAdapter;
    private ImageView null_image;
    protected Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.GET_ORDER_ACTION_RESULT:
                    handleList((List<OrderStatusBean>) msg.obj);
                    break;
            }
        }
    };

    private void handleList(List<OrderStatusBean> bean) {
        if (bean.size() > 0) {
            mXListView.setVisibility(View.VISIBLE);
            mAdapter.setDatas(bean);
            mAdapter.notifyDataSetChanged();
            mXListView.stopRefresh(true);
        } else {
            mXListView.setVisibility(View.GONE);
            mXListView.stopRefresh(false);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_all_order, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // initHandler();
        initController();
        initView();
        mController.sendAsyncMessage(IDiyMessage.GET_ORDER_ACTION, -2);
    }


    protected void initController() {
        mController = new OrderController(getActivity());
        mController.setListener(this);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    protected void initView() {
        mXListView = (XListView) getActivity().findViewById(R.id.all_order_lv);
//        null_image = (ImageView) getActivity().findViewById(R.id.all_order_null_view).findViewById(R.id.null_image);
        mXListView.setPullRefreshEnable(true);
        mXListView.setPullLoadEnable(true);
        mXListView.setXListViewListener(this);
        mAdapter = new AllOrderAdapter(getActivity());
        mXListView.setAdapter(mAdapter);
    }


    @Override
    public void onRefresh() {
        mController.sendAsyncMessage(IDiyMessage.GET_ORDER_ACTION, -2);

    }

    @Override
    public void onLoadMore() {

    }
}
