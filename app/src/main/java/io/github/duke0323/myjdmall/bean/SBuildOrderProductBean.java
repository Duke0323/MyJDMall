package io.github.duke0323.myjdmall.bean;

/**
 *    "buyCount": 购买数量,
 "type": "商品版本",
 "pid": 商品id
 * Created by ${Duke} on 2016/7/19.
 */
public class SBuildOrderProductBean {
    private int buyCount;
    private String type;
    private long pid;

    public SBuildOrderProductBean(int buyCount, String type, long pid) {
        this.buyCount = buyCount;
        this.type = type;
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "SBuildOrderProductBean{" +
                "buyCount=" + buyCount +
                ", type='" + type + '\'' +
                ", pid=" + pid +
                '}';
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
}
