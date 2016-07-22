package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import io.github.duke0323.myjdmall.Controller.BaseController;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class BaseActivity extends AppCompatActivity {
    private BaseController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected void initController() {

    }

    public void goBack(View view) {
        finish();
    }
}
