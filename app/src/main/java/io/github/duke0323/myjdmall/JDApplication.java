package io.github.duke0323.myjdmall;

import android.app.Application;

import io.github.duke0323.myjdmall.bean.RLogin;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class JDApplication extends Application {
    //全局成员变量
    public  RLogin mUserInfo;



    @Override
    public void onCreate() {
        super.onCreate();
    }
}
