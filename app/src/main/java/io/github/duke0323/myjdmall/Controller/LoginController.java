package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;

import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.bean.UserBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.db.UserDao;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class LoginController extends BaseController {


    public LoginController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.LOGIN_ACTION:
                RResultBean rResultBean = login((String) values[0], (String) values[1]);
                mListener.onModelChange(IDiyMessage.LOGIN_ACTION_RESULT, rResultBean);
                break;
            case IDiyMessage.SAVE_ACTION:
                mListener.onModelChange(IDiyMessage.SAVE_ACTION_RESULT, getSave());
                break;

        }
    }



    public UserBean getSave() {
        UserDao dao = new UserDao(context);
        return dao.queryLogin();
    }


    private RResultBean login(String name, String pwd) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("username", name);
        params.put("pwd", pwd);
        //登录
        String result = HttpUtils.getInstance().doPost(HttpConst.LOGIN_URL, params);
        return JSON.parseObject(result, RResultBean.class);

    }

}
