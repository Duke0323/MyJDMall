package io.github.duke0323.myjdmall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.github.duke0323.myjdmall.activity.LoginActivity;

/**
 * 判断是否登陆逻辑
 * Created by ${Duke} on 2016/7/10.
 */

public class LoginUtil extends Activity {

    private int REQUEST_CODE_LOGIN = 1;
    static LoginCallback mCallback;

    public interface LoginCallback {
        void onLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
        if (requestCode == REQUEST_CODE_LOGIN && resultCode == RESULT_OK && mCallback != null) {
            mCallback.onLogin();
        }
        mCallback = null;
    }
    public static void checkLogin(Context context, LoginCallback callback) {
        //此处检查当前的登录状态
        boolean login = AccountMgr.get().isLogin();
        if (login) {
            callback.onLogin();
        } else {
            mCallback = callback;
            Intent intent = new Intent(context, LoginUtil.class);
            context.startActivity(intent);
        }
    }

    public static void checkLogin(Context context, LoginCallback logged, LoginCallback callback) {
        //此处检查当前的登录状态
        boolean login = AccountMgr.get().isLogin();
        if (login) {
            logged.onLogin();
        } else {
            mCallback = callback;
            Intent intent = new Intent(context, LoginUtil.class);
            context.startActivity(intent);
        }
    }


}