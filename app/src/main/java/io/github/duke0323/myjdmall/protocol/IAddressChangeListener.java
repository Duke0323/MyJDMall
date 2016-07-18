package io.github.duke0323.myjdmall.protocol;

import io.github.duke0323.myjdmall.bean.RecAddressBean;

/**
 * Created by ${Duke} on 2016/7/18.
 */
public interface IAddressChangeListener {
    void onChange(RecAddressBean province, RecAddressBean city, RecAddressBean area);
}
