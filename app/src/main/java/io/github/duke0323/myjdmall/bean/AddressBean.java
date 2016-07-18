package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/17.
 */
public class AddressBean {
    /* "id": 地址id,
           "isDefault": false,
           "receiverName": "接收人",
           "receiverAddress": "具体地址",
           "receiverPhone": "手机号"
           */
    private int id;
    private boolean isDefault;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;

    @Override
    public String toString() {
        return "AddressBean{" +
                "id=" + id +
                ", isDefault=" + isDefault +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
