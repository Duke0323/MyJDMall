package io.github.duke0323.myjdmall.protocol;

/**
 * Created by ${Duke} on 2016/7/20.
 */
public interface IPayListener {
    void onPay(String account,String pwd,String pay_pwd);
}
