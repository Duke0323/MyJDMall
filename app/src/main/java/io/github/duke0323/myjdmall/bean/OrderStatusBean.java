package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/21.
 */
public class OrderStatusBean {

    /*
    * "items": [
               "商品图片"
           ],
           "oid": 订单id,
           "orderNum": "订单号",
           "paid": 是否支付,
           "status": 订单状态,
           "tn": "订单令牌",
           "totalPrice": 订单总金额*/
    private String items;
    private long oid;
    private String orderNum;
    private boolean paid;
    private int status;//-1取消订单 0待支付 1待发货 2 待收货3 完成交易
    private String tn;
    private double totalPrice;

    @Override
    public String toString() {
        return "OrderStatusBean{" +
                "items='" + items + '\'' +
                ", oid=" + oid +
                ", orderNum='" + orderNum + '\'' +
                ", paid=" + paid +
                ", status=" + status +
                ", tn='" + tn + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
