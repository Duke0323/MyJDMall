package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/16.
 */
public class CommentDetailBean {
   /* "id": 评论id,
            "imgUrls": ["评论图片路径"],
            "rate": 星星数,
            "loveCount": 喜欢数,
            "commentTime": "2016-03-06 21:55:40"（评论时间）,
            "buyTime": "2016-03-02 11:12:19"（购买时间）,
            "userLevel": 用户等级,
            "subComment": 回复评论数,
            "userName": "2",
            "comment": "评论内容",
            "userImg": "用户头像路径",
            "productType": "产品版本类型"*/
    int id;
    String imgUrls;
    int rate;
    int loveCount;
    String commentTime;
    String buyTime;
    int userLevel;
    int subComment;
    String userName;
    String comment;
    String userImg;
    String productType;

    @Override
    public String toString() {
        return "CommentDetailBean{" +
                "id=" + id +
                ", imgUrls='" + imgUrls + '\'' +
                ", rate=" + rate +
                ", loveCount=" + loveCount +
                ", commentTime='" + commentTime + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", userLevel=" + userLevel +
                ", subComment=" + subComment +
                ", userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", userImg='" + userImg + '\'' +
                ", productType=" + productType +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public int getSubComment() {
        return subComment;
    }

    public void setSubComment(int subComment) {
        this.subComment = subComment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
