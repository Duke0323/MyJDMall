package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.github.duke0323.myjdmall.bean.AddressBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.bean.RecAddressBean;
import io.github.duke0323.myjdmall.bean.ShopListBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/17.
 */
public class ShopCarController extends BaseController {


    public ShopCarController(Context context) {
        super(context);

    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.LOAD_SHOPCAR_ACTION:
                mListener.onModelChange(IDiyMessage.LOAD_SHOPCAR_ACTION_RESULT, loadShopList());
                break;
            case IDiyMessage.DELETE_SHOPCAR_ACTION:
                mListener.onModelChange(IDiyMessage.DELETE_SHOPCAR_ACTION_RESULT, deleteShopCar((Integer) values[0]));
                break;
            case IDiyMessage.RECEIVE_ADDRESS_ACTION:
                mListener.onModelChange(IDiyMessage.RECEIVE_ADDRESS_ACTION_RESULT, setAddress((Boolean) values[0]));
                break;
            case IDiyMessage.GET_PROVINCE_ACTION:
                mListener.onModelChange(IDiyMessage.GET_PROVINCE_ACTION_RESULT, getProvince());
                break;
            case IDiyMessage.GET_CITY_ACTION:
                mListener.onModelChange(IDiyMessage.GET_CITY_ACTION_RESULT, getCity((String)values[0]));
                break;
            case IDiyMessage.GET_AREA_ACTION:
                mListener.onModelChange(IDiyMessage.GET_AREA_ACTION_RESULT, getArea((String) values[0]));
                break;
        }
    }

    private List<RecAddressBean> getArea(String value) {
        String jsonStr = HttpUtils.getInstance().doGet(HttpConst.AREA_URL+"?fcode="+value);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseArray(rResultBean.getResult(), RecAddressBean.class);
        }
        return new ArrayList<>();
    }

    private List<RecAddressBean> getCity(String value) {

        String jsonStr = HttpUtils.getInstance().doGet(HttpConst.CITY_URL+"?fcode="+value);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseArray(rResultBean.getResult(), RecAddressBean.class);
        }
        return new ArrayList<>();
    }


    private List<RecAddressBean> getProvince() {
        String jsonStr = HttpUtils.getInstance().doGet(HttpConst.PROVINCE_URL);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseArray(rResultBean.getResult(), RecAddressBean.class);
        }
        return new ArrayList<>();
    }

    private List<AddressBean> setAddress(Boolean isDefault) {

        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("userId", mId);
        params.put("isDefault", String.valueOf(isDefault));
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.RECEIVE_ADDRESS_URL, params);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseArray(rResultBean.getResult(), AddressBean.class);
        }
        return new ArrayList<>();
    }

    private RResultBean deleteShopCar(Integer id) {

        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("userId", mId);
        params.put("id", String.valueOf(id));

        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.DELSHOPCAR_URL, params);
        return JSON.parseObject(jsonStr, RResultBean.class);


    }

    private List<ShopListBean> loadShopList() {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("userId", mId);
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.SHOPCAR_URL, params);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseArray(rResultBean.getResult(), ShopListBean.class);
        }
        return new ArrayList<>();
    }


    
}
