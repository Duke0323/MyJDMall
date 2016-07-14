package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.bean.AdsBean;
import io.github.duke0323.myjdmall.bean.AdsResultBean;
import io.github.duke0323.myjdmall.bean.LoginResultBean;
import io.github.duke0323.myjdmall.bean.RecoBean;
import io.github.duke0323.myjdmall.bean.SecKillBean;
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
                mListener.onModelChange(IDiyMessage.HOME_ADS_ACTION_RESULT, loadAds((Integer) values[0]));
                break;
            case IDiyMessage.HOME_ADS2_ACTION:
                mListener.onModelChange(IDiyMessage.HOME_ADS2_ACTION_RESULT, loadAds((Integer) values[0]));
                break;
            case IDiyMessage.HOME_SECKILL_ACTION:
                mListener.onModelChange(IDiyMessage.HOME_SECKILL_ACTION_RESULT, loadSecKill());
                break;
            case IDiyMessage.HOME_RECOMMEMD_ACTION:
                mListener.onModelChange(IDiyMessage.HOME_RECOMMEMD_ACTION_RESULT, loadRecomend());
                break;

        }
    }

    private List loadRecomend() {
        List<RecoBean> secKill = new ArrayList<>();
        String jsonStr = HttpUtils.doGet(HttpConst.RECOMMEND_URL);
        LoginResultBean resultBean = JSON.parseObject(jsonStr, LoginResultBean.class);
        if (resultBean.isSuccess()) {
            String result = resultBean.getResult();
            try {
                JSONObject jsonObject = new JSONObject(result);
                String rows = jsonObject.getString("rows");
                return JSON.parseArray(rows, RecoBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ArrayList<RecoBean>();
    }

    private List loadSecKill() {
        List<SecKillBean> secKill = new ArrayList<>();
        String jsonStr = HttpUtils.doGet(HttpConst.SECKILL_URL);
        LoginResultBean resultBean = JSON.parseObject(jsonStr, LoginResultBean.class);
        if (resultBean.isSuccess()) {
            String result = resultBean.getResult();
            try {
                JSONObject jsonObject = new JSONObject(result);
                String rows = jsonObject.getString("rows");
                return JSON.parseArray(rows, SecKillBean.class);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ArrayList<SecKillBean>();

    }

    private List<AdsResultBean> loadAds(int type) {
        List<AdsResultBean> result = new ArrayList<>();
        String jsonStr = HttpUtils.doGet(HttpConst.ADS_URL + "?adKind=" + type);
        AdsBean adsBean = JSON.parseObject(jsonStr, AdsBean.class);
        if (adsBean.isSuccess()) {
            return JSON.parseArray(adsBean.getResult(), AdsResultBean.class);
        }
        return new ArrayList<AdsResultBean>();
    }
}
