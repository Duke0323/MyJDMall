package io.github.duke0323.myjdmall.bean;

import java.util.ArrayList;

/**
 * Created by ${Duke} on 2016/7/19.
 */
public class SBuildOrderParams {
    private long addrId;
    private int payWay=-1;
    private String userId;
    private ArrayList<SBuildOrderProductBean> products;

    @Override
    public String toString() {
        return "SBuildOrderParams{" +
                "addrId=" + addrId +
                ", payWay=" + payWay +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }



    public long getAddrId() {
        return addrId;
    }

    public void setAddrId(long addrId) {
        this.addrId = addrId;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<SBuildOrderProductBean> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<SBuildOrderProductBean> products) {
        this.products = products;
    }
}
