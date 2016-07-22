package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;

import io.github.duke0323.myjdmall.bean.SignUpBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/10.
 *
 */
public class SignUpController extends BaseController {
    public SignUpController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        if (action == IDiyMessage.SIGNUP_ACTION) {
            SignUpBean signUpBean = signUp((String) values[0], (String) values[1]);
            mListener.onModelChange(IDiyMessage.SIGNUP_ACTION_RESULT, signUpBean);
        }
    }

    private SignUpBean signUp(String name, String pwd) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("username", name);
        params.put("pwd", pwd);
        String result = HttpUtils.getInstance().doPost(HttpConst.SIGNUP_URL, params);
        return JSON.parseObject(result, SignUpBean.class);

    }
}
