package io.github.duke0323.myjdmall.Controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import io.github.duke0323.myjdmall.BuildConfig;
import io.github.duke0323.myjdmall.JDApplication;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public abstract class BaseController {
    protected IModelChangeListener mListener;
    protected String mId = null;
    protected Context context;

    public BaseController(Context context) {
        this.context = context;
        JDApplication application = (JDApplication) ((Activity) context).getApplication();
        if(application.mUserInfo!=null) {
            mId = application.mUserInfo.getId();
            if (BuildConfig.DEBUG)
                Log.d("BaseControllermId", mId);
        }
    }


    public void setListener(IModelChangeListener listener) {
        mListener = listener;
    }

    public void sendAsyncMessage(final int action, final Object... values) {
        new Thread() {
            public void run() {
                handlerMessage(action, values);
            }
        }.start();
    }

    protected abstract void handlerMessage(int action, Object... values);
}
