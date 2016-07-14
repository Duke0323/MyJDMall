package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class SecKillBean {

    /**
     * allPrice : 原价
     * pointPrice : 秒杀价格
     * iconUrl : 商品图片路径
     * timeLeft : 秒杀剩余时间（分钟）
     * type : 秒杀类型（1抢年货，2超值，3热卖）
     * productId : 商品id
     */

    private double allPrice;
    private double pointPrice;
    private String iconUrl;
    private int timeLeft;
    private int type;
    private long productId;

    @Override
    public String toString() {
        return "SecKillBean{" +
                "allPrice=" + allPrice +
                ", pointPrice=" + pointPrice +
                ", iconUrl='" + iconUrl + '\'' +
                ", timeLeft=" + timeLeft +
                ", type=" + type +
                ", productId=" + productId +
                '}';
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public double getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(double pointPrice) {
        this.pointPrice = pointPrice;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
