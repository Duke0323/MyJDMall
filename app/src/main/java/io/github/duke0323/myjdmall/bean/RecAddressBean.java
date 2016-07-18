package io.github.duke0323.myjdmall.bean;

/**
 * 省市县编码
 * Created by ${Duke} on 2016/7/18.
 */
public class RecAddressBean {
  private String name;
  private String code;

    @Override
    public String toString() {
        return "RecAddressBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
