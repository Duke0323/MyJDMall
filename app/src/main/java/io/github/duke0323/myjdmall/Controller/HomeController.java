package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.bean.AdsBean;
import io.github.duke0323.myjdmall.bean.AdsResultBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/12.
 */
public class HomeController extends BaseController {
    public HomeController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.HOME_ADS_ACTION:
                mListener.onModelChange(IDiyMessage.HOME_ADS_ACTION_RESULT, loadAds());
                break;
        }
    }

    private List<AdsResultBean> loadAds() {
        List<AdsResultBean> result = new ArrayList<>();
        String jsonStr = HttpUtils.doGet(HttpConst.ADS_URL + "?adKind=1");
        AdsBean adsBean = JSON.parseObject(jsonStr, AdsBean.class);
        if (adsBean.isSuccess()) {
            return JSON.parseArray(adsBean.getResult(), AdsResultBean.class);
        }
        return new ArrayList<>();
    }
}
