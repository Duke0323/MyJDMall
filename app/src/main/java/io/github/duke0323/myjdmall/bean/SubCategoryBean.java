package io.github.duke0323.myjdmall.bean;

import java.util.List;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class SubCategoryBean {


    private int id;
    private String name;
    private List<CategoryBean> thirdCategory;

    @Override
    public String toString() {
        return "SubCategoryBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thirdCategory=" + thirdCategory +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryBean> getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(List<CategoryBean> thirdCategory) {
        this.thirdCategory = thirdCategory;
    }
}
