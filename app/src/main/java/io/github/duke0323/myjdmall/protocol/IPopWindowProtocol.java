package io.github.duke0323.myjdmall.protocol;

import android.view.View;

/**
 * Created by ${Duke} on 2016/7/14.
 */
public interface IPopWindowProtocol {
    void initView();

    void onShow(View anchorView);

    void onDismiss();


}
