package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/20.
 */
public class RBuildOrderParams {
    long oid;
    String buyTime;
    double allPrice;
    double freight;
    int errorType;
    String orderNum;
    int payWay;
    String pname;
    String tn;
    double totalPrice;

    @Override
    public String toString() {
        return "RBuildOrderParams{" +
                "oid=" + oid +
                ", buyTime='" + buyTime + '\'' +
                ", allPrice=" + allPrice +
                ", freight=" + freight +
                ", errorType=" + errorType +
                ", orderNum='" + orderNum + '\'' +
                ", payWay=" + payWay +
                ", pname='" + pname + '\'' +
                ", tn='" + tn + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
