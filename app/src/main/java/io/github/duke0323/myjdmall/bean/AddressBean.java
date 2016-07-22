package io.github.duke0323.myjdmall.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${Duke} on 2016/7/17.
 */
public class AddressBean implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeByte(this.isDefault ? (byte) 1 : (byte) 0);
        dest.writeString(this.receiverName);
        dest.writeString(this.receiverAddress);
        dest.writeString(this.receiverPhone);
    }

    public AddressBean() {
    }

    protected AddressBean(Parcel in) {
        this.id = in.readInt();
        this.isDefault = in.readByte() != 0;
        this.receiverName = in.readString();
        this.receiverAddress = in.readString();
        this.receiverPhone = in.readString();
    }

    public static final Parcelable.Creator<AddressBean> CREATOR = new Parcelable.Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}
