package io.github.duke0323.myjdmall.protocol;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public interface IModelChangeListener {
    void onModelChange(int action, Object... values);
}
