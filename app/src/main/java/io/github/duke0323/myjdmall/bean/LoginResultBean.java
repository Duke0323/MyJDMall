package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class LoginResultBean {

    /**
     * errorMsg :
     * result : {"id":1,"userIcon":"/img/user/1.jpg","userLevel":1,"userName":"2","waitPayCount":86,"waitReceiveCount":0}
     * success : true
     */

    private String errorMsg;
    /**
     * id : 1
     * userIcon : /img/user/1.jpg
     * userLevel : 1
     * userName : 2
     * waitPayCount : 86
     * waitReceiveCount : 0
     */

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
