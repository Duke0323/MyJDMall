package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class RecoBean {

    /**
     * price : 商品价格
     * name : 商品名称
     * iconUrl : 商品图片
     * productId : 商品id
     */

    private double price;
    private String name;
    private String iconUrl;
    private long productId;

    @Override
    public String toString() {
        return "RecoBean{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", productId=" + productId +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
