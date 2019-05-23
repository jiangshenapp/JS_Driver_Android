package com.js.driver.model.request;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/23
 * desc   :
 * version: 3.0.0
 */
public class ParkVerifiedRequest {

    private String companyName;
    private String companyType;
    private String registrationNumber;
    private String address;
    private String detailAddress;
    private String businessLicenceImage;

    public ParkVerifiedRequest(String companyName, String companyType, String registrationNumber, String address, String detailAddress, String businessLicenceImage) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.detailAddress = detailAddress;
        this.businessLicenceImage = businessLicenceImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getBusinessLicenceImage() {
        return businessLicenceImage;
    }

    public void setBusinessLicenceImage(String businessLicenceImage) {
        this.businessLicenceImage = businessLicenceImage;
    }
}
