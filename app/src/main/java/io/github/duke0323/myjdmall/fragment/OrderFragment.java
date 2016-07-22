package io.github.duke0323.myjdmall.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.OrderController;
import io.github.duke0323.myjdmall.adapter.JDBaseAdapter;
import io.github.duke0323.myjdmall.bean.OrderStatusBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.ui.XListView;

/**
 * Created by ${Duke} on 2016/7/22.
 */
public abstract class OrderFragment extends BaseFragemnt implements IModelChangeListener, XListView.IXListViewListener {
    protected XListView mXListView;
    protected JDBaseAdapter<OrderStatusBean> mAdapter;
    protected Handler handler;

    protected void initHandler() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case IDiyMessage.GET_ORDER_ACTION_RESULT:
                        handleList((List<OrderStatusBean>) msg.obj);
                        break;
                    case IDiyMessage.CONFIRM_ORDER_ACTION_RESULT:
                        handleConfirm((RResultBean)msg.obj);
                        break;
                }
            }
        };
    }

    private void handleConfirm(RResultBean bean) {
        if(bean.isSuccess()) {
            Toast.makeText(getActivity(), "确认收货成功", Toast.LENGTH_SHORT).show();
            mController.sendAsyncMessage(IDiyMessage.GET_ORDER_ACTION, 2);
        }else {
            Toast.makeText(getActivity(), "确认收货失败", Toast.LENGTH_SHORT).show();
        }
    }


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
    protected void initController() {
        mController = new OrderController(getActivity());
        mController.setListener(this);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    protected abstract void initView();
}
