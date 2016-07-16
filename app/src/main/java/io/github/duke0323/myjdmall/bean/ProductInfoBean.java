package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/16.
 */
public class ProductInfoBean {
    private long id;
    private String imgUrls;
    private double price;
    private boolean ifSaleOneself;
    private String name;
    private long recomProductId;
    private int stockCount;
    private int commentCount;
    private String typeList;
    private long favcomRate;
    private String recomProduct;

    @Override
    public String toString() {
        return "ProductInfoBean{" +
                "id=" + id +
                ", imgUrls='" + imgUrls + '\'' +
                ", price=" + price +
                ", ifSaleOneself=" + ifSaleOneself +
                ", name='" + name + '\'' +
                ", recomProductId=" + recomProductId +
                ", stockCount=" + stockCount +
                ", commentCount=" + commentCount +
                ", typeList='" + typeList + '\'' +
                ", favcomRate=" + favcomRate +
                ", recomProduct='" + recomProduct + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIfSaleOneself() {
        return ifSaleOneself;
    }

    public void setIfSaleOneself(boolean ifSaleOneself) {
        this.ifSaleOneself = ifSaleOneself;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRecomProductId() {
        return recomProductId;
    }

    public void setRecomProductId(long recomProductId) {
        this.recomProductId = recomProductId;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public long getFavcomRate() {
        return favcomRate;
    }

    public void setFavcomRate(long favcomRate) {
        this.favcomRate = favcomRate;
    }

    public String getRecomProduct() {
        return recomProduct;
    }

    public void setRecomProduct(String recomProduct) {
        this.recomProduct = recomProduct;
    }
}
