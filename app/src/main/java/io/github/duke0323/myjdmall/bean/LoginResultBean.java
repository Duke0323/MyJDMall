package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class LoginResultBean {



    private String errorMsg;
    private String result;
    private boolean success;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
