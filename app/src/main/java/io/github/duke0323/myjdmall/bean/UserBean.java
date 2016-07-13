package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class UserBean {
    private String name;
    private String pwd;

    public UserBean(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
