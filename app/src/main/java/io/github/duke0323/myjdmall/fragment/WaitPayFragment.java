package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.OrderController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.adapter.WaitPayAdapter;
import io.github.duke0323.myjdmall.bean.OrderStatusBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.ICancelOrderListener;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.ui.LoadingDialog;
import io.github.duke0323.myjdmall.ui.XListView;

/**
 * 待支付
 * Created by ${Duke} on 2016/7/21.
 */
public class WaitPayFragment extends BaseFragemnt implements IModelChangeListener, ICancelOrderListener, XListView.IXListViewListener {
    private XListView mXListView;
    private WaitPayAdapter mAdapter;
    private ImageView null_image;
    protected Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.GET_ORDER_ACTION_RESULT:
                    handleList((List<OrderStatusBean>) msg.obj);
                    break;
                case IDiyMessage.CANCEL_ORDER_ACTION_RESULT:
                    cancelOrder((RResultBean) msg.obj);
                    break;
            }
        }
    };
    private LoadingDialog mDialog;

    private void cancelOrder(RResultBean bean) {
        if (bean.isSuccess()) {
            mDialog.dismiss();
            Toast.makeText(getActivity(), "取消订单", Toast.LENGTH_SHORT).show();
            mXListView.startRefresh();

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_wait_pay, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // initHandler();
        initController();
        initView();
        mController.sendAsyncMessage(IDiyMessage.GET_ORDER_ACTION, 0);
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
        mDialog = new LoadingDialog(getActivity(), R.style.CustomDialog);
        mXListView = (XListView) getActivity().findViewById(R.id.wait_pay_order_lv);
        null_image = (ImageView) getActivity().findViewById(R.id.wait_pay_null_view).findViewById(R.id.null_image);
        mXListView.setPullRefreshEnable(true);
        mXListView.setPullLoadEnable(true);
        mXListView.setXListViewListener(this);
        mAdapter = new WaitPayAdapter(getActivity());
        mAdapter.setlistener(this);
        mXListView.setAdapter(mAdapter);
    }


    @Override
    public void onRefresh() {
        mController.sendAsyncMessage(IDiyMessage.GET_ORDER_ACTION, 0);

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onCancel(String oid) {
        mDialog.show();
        mController.sendAsyncMessage(IDiyMessage.CANCEL_ORDER_ACTION, oid);
    }
}
