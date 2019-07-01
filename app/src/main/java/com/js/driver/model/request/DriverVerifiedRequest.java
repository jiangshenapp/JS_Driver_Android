package com.js.driver.model.request;

import retrofit2.http.Query;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/22
 * desc   :
 * version: 3.0.0
 */
public class DriverVerifiedRequest {

    private String idImage;
    private String idHandImage;
    private String driverImage;
    private String cyzgzImage;
    private String personName;
    private String idCode;
    private String address;
    private String driverLevel;

    public DriverVerifiedRequest(String idImage, String idHandImage, String driverImage, String cyzgzImage, String personName, String idCode, String address, String driverLevel) {
        this.idImage = idImage;
        this.idHandImage = idHandImage;
        this.driverImage = driverImage;
        this.cyzgzImage = cyzgzImage;
        this.personName = personName;
        this.idCode = idCode;
        this.address = address;
        this.driverLevel = driverLevel;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getIdHandImage() {
        return idHandImage;
    }

    public void setIdHandImage(String idHandImage) {
        this.idHandImage = idHandImage;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }

    public String getCyzgzImage() {
        return cyzgzImage;
    }

    public void setCyzgzImage(String cyzgzImage) {
        this.cyzgzImage = cyzgzImage;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverLevel() {
        return driverLevel;
    }

    public void setDriverLevel(String driverLevel) {
        this.driverLevel = driverLevel;
    }
}
