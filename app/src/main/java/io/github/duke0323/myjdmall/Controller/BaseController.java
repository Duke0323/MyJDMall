package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import io.github.duke0323.myjdmall.protocol.IModelChangeListener;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public abstract class BaseController {
    protected IModelChangeListener mListener;

    protected Context context;

    public BaseController(Context context) {
        this.context = context;
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
