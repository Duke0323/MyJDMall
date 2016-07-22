package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.github.duke0323.myjdmall.bean.OrderStatusBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/21.
 */
public class OrderController extends BaseController {
    public OrderController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.GET_ORDER_ACTION:
                List<OrderStatusBean> orderStatusBeen = handleWaitOrder((Integer) values[0]);
                mListener.onModelChange(IDiyMessage.GET_ORDER_ACTION_RESULT, orderStatusBeen);
                break;
            case IDiyMessage.CONFIRM_ORDER_ACTION:
                RResultBean rResultBean = confirmOrder((String) values[0]);
                mListener.onModelChange(IDiyMessage.CONFIRM_ORDER_ACTION_RESULT, rResultBean);
                break;
            case IDiyMessage.CANCEL_ORDER_ACTION:
                RResultBean rResultBean1 = cancelOrder((String) values[0]);
                mListener.onModelChange(IDiyMessage.CANCEL_ORDER_ACTION_RESULT, rResultBean1);
                break;
        }
    }

    private RResultBean cancelOrder(String oid) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("userId", mId);
        params.put("oid", oid);
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.CANCELORDER_URL, params);
        return JSON.parseObject(jsonStr, RResultBean.class);
    }

    private RResultBean confirmOrder(String oid) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("userId", mId);
        params.put("oid", oid);
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.CONFIRMORDER_URL, params);
        return JSON.parseObject(jsonStr, RResultBean.class);
    }

    private List<OrderStatusBean> handleWaitOrder(Integer value) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("status", String.valueOf(value));
        params.put("userId", mId);
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.GETORDERBYSTATUS_URL, params);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseArray(rResultBean.getResult(), OrderStatusBean.class);
        }
        return new ArrayList<>();
    }
}
