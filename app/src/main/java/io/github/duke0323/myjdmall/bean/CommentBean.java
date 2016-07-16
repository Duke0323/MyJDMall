package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/16.
 */
public class CommentBean {

    String imgUrls;
    String userName;
    int type;
    String comment;
    String time;
    int rate;

    @Override
    public String toString() {
        return "CommentBean{" +
                "imgUrls='" + imgUrls + '\'' +
                ", userName='" + userName + '\'' +
                ", type=" + type +
                ", comment='" + comment + '\'' +
                ", time='" + time + '\'' +
                ", rate=" + rate +
                '}';
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
