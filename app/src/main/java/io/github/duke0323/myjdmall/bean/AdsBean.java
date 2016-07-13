package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/12.
 */
public class AdsBean {

    /**
     * errorMsg :
     * result : [{"adKind":1,"adUrl":"/img/a1.jpg","id":1,"type":1,"webUrl":""},{"adKind":2,"adUrl":"/img/ad6.jpg","id":2,"type":2},{"adKind":1,"adUrl":"/img/a5.jpg","id":3,"type":1},{"adKind":1,"adUrl":"/img/a4.jpg","id":4,"type":1},{"adKind":1,"adUrl":"/img/a3.jpg","id":5,"type":1},{"adKind":1,"adUrl":"/img/a2.jpg","id":6,"type":1}]
     * success : true
     */

    private String errorMsg;
    private boolean success;
    /**
     * adKind : 1
     * adUrl : /img/a1.jpg
     * id : 1
     * type : 1
     * webUrl :
     */

    private String result;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
