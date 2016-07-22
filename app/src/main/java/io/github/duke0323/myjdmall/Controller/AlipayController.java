package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;

import io.github.duke0323.myjdmall.bean.AlipayBean;
import io.github.duke0323.myjdmall.bean.PayBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/20.
 */
public class AlipayController extends BaseController {

    private String mTnPay;

    public AlipayController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.ALIPAY_ACTION:
                mListener.onModelChange(IDiyMessage.ALIPAY_ACTION_RESULT, loadAlipay((String) values[0]));
                break;
            case IDiyMessage.PAY_ACTION:
                mListener.onModelChange(IDiyMessage.PAY_ACTION_RESULT, loadpay((String) values[0], (String) values[1], (String) values[2],(String) values[3]));
                break;
        }
    }

    private PayBean loadpay(String account, String pwd, String pay_pwd,String tn) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("account", account);
        params.put("apwd", pwd);
        params.put("ppwd", pay_pwd);
        params.put("tn", tn);
        params.put("userId", mId);
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.PAY_URL, params);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseObject(rResultBean.getResult(), PayBean.class);
        }
        return null;
    }

    private AlipayBean loadAlipay(String tn) {
        mTnPay = tn;
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("tn", tn);
        params.put("userId", mId);
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.GETPAYINFO_URL, params);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseObject(rResultBean.getResult(), AlipayBean.class);
        }
        return null;
    }
}
