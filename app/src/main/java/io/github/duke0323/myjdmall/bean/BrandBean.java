package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/15.
 */
public class BrandBean {
    long id;
    String name;

    @Override
    public String toString() {
        return "BrandBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
