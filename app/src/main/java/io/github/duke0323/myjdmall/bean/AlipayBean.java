package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/20.
 */
public class AlipayBean {
    /*
    * "oinfo": "JD+订单号",
      "tn": "订单令牌",
      "pname": "京东商品",
      "payTime": 购买时间,
      "totalPrice": 总金额
    * */
    String oinfo;
    String tn;
    String pname;
    String payTime;
    double totalPrice;

    @Override
    public String toString() {
        return "AlipayBean{" +
                "oinfo='" + oinfo + '\'' +
                ", tn='" + tn + '\'' +
                ", pname='" + pname + '\'' +
                ", payTime='" + payTime + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getOinfo() {
        return oinfo;
    }

    public void setOinfo(String oinfo) {
        this.oinfo = oinfo;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
