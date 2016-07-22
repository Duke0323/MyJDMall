package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/21.
 */
public class PayBean {
    String oid;

    @Override
    public String toString() {
        return "PayBean{" +
                "oid='" + oid + '\'' +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
