package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public  class ResultBean {
    private int id;
    private String userIcon;
    private int userLevel;
    private String userName;
    private int waitPayCount;
    private int waitReceiveCount;

    @Override
    public String toString() {
        return "ResultBean{" +
                "id=" + id +
                ", userIcon='" + userIcon + '\'' +
                ", userLevel=" + userLevel +
                ", userName='" + userName + '\'' +
                ", waitPayCount=" + waitPayCount +
                ", waitReceiveCount=" + waitReceiveCount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWaitPayCount() {
        return waitPayCount;
    }

    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public int getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public void setWaitReceiveCount(int waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }
}
