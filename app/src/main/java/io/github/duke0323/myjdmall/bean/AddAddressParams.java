package io.github.duke0323.myjdmall.bean;

/**
 * Created by ${Duke} on 2016/7/19.
 */
public class AddAddressParams {
    private String name;
    private String phone;
    private String provinceCode;
    private String cityCode;
    private String distCode;
    private String addressDetails;
    private boolean isDefault;

    public AddAddressParams(String name, String phone, String provinceCode, String cityCode, String distCode, String addressDetails, boolean isDefault) {
        this.name = name;
        this.phone = phone;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.distCode = distCode;
        this.addressDetails = addressDetails;
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "AddAddressParams{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", distCode='" + distCode + '\'' +
                ", addressDetails='" + addressDetails + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
