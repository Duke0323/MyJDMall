package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;

import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class ResetController extends BaseController {
    public ResetController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        if (action == IDiyMessage.RESET_ACTION) {
            RResultBean rResultBean = reset((String) values[0]);
            mListener.onModelChange(IDiyMessage.RESET_ACTION_RESULT, rResultBean);
        }
    }

    private RResultBean reset(String name) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("username",name);
        String result = HttpUtils.getInstance().doPost(HttpConst.RESET_URL, params);
        return JSON.parseObject(result, RResultBean.class);

    }
}
