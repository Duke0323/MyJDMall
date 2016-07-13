package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class SignUpBean {

    /**
     * success : true
     * errorMsg :
     * result : {"id":"用户id"}
     */

    private boolean success;
    private String errorMsg;
    /**
     * id : 用户id
     */

    private ResultBean result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
