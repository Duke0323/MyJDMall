package io.github.duke0323.myjdmall.bean;

/**
 * 商品接口列表的发送参数
 * Created by ${Duke} on 2016/7/14.
 */
public class SProductList {
    public static final int ALL_SORT=1;
    public static final int NEW_SORT=2;
    public static final int COMMENT_SORT=3;
    public  int sortType=0;//（1-销量 2-价格高到低 3-价格低到高）
    public static final int SALE_SORT = 1;
    public static final int PRICE_UP2DOWN = 2;
    public static final int PRICE_DOWN2UP = 3;
    public int mCategoryid;
    public  int filterType;//排序类型（1-综合 2-新品 3-评价）
    public  int deliverChoose=0;//选择类型（0-代表无选择 1代表京东配送 2-代表货到付款 4-代表仅看有货 3代表条件1+2 5代表条件1+4 6代表条件2+4）
    public double minPrice;
    public double maxPrice;
    public long brandId;

}
