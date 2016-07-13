package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.utils.ActivityUtils;

public class SplashActivity extends BaseActivity {

    private android.widget.ImageView logoiv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        alphaAnim();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ActivityUtils.start(SplashActivity.this, LoginActivity.class, true);
            }
        }, 2500);
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
        this.logoiv = (ImageView) findViewById(R.id.logo_iv);

    }

    private void alphaAnim() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(2500);
        alphaAnimation.setFillAfter(true);
        logoiv.startAnimation(alphaAnimation);
    }

}
