package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/16.
 */
public class CommentCountBean {


    private int moderateCom;
    private int allComment;
    private int hasImgCom;
    private int negativeCom;
    private int positiveCom;

    @Override
    public String toString() {
        return "CommentCountBean{" +
                "moderateCom=" + moderateCom +
                ", allComment=" + allComment +
                ", hasImgCom=" + hasImgCom +
                ", negativeCom=" + negativeCom +
                ", positiveCom=" + positiveCom +
                '}';
    }

    public int getModerateCom() {
        return moderateCom;
    }

    public void setModerateCom(int moderateCom) {
        this.moderateCom = moderateCom;
    }

    public int getAllComment() {
        return allComment;
    }

    public void setAllComment(int allComment) {
        this.allComment = allComment;
    }

    public int getHasImgCom() {
        return hasImgCom;
    }

    public void setHasImgCom(int hasImgCom) {
        this.hasImgCom = hasImgCom;
    }

    public int getNegativeCom() {
        return negativeCom;
    }

    public void setNegativeCom(int negativeCom) {
        this.negativeCom = negativeCom;
    }

    public int getPositiveCom() {
        return positiveCom;
    }

    public void setPositiveCom(int positiveCom) {
        this.positiveCom = positiveCom;
    }
}
