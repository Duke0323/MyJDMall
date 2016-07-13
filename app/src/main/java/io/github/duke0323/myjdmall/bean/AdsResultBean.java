package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/12.
 */
public class AdsResultBean {
    private int adKind;
    private String adUrl;
    private int id;
    private int type;
    private String webUrl;

    @Override
    public String toString() {
        return "AdsResultBean{" +
                "adKind=" + adKind +
                ", adUrl='" + adUrl + '\'' +
                ", id=" + id +
                ", type=" + type +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }

    public int getAdKind() {
        return adKind;
    }

    public void setAdKind(int adKind) {
        this.adKind = adKind;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
