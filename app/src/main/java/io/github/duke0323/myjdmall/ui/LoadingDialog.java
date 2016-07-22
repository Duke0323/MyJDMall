package io.github.duke0323.myjdmall.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import io.github.duke0323.myjdmall.R;

/**
 * Created by ${Duke} on 2016/7/22.
 */
public class LoadingDialog extends AlertDialog {

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_view);
        initView();
    }

    private void initView() {
        ImageView loading_iv = (ImageView) findViewById(R.id.loading_iv);
        AnimationDrawable drawable = (AnimationDrawable) loading_iv.getDrawable();
        drawable.start();
    }

}
