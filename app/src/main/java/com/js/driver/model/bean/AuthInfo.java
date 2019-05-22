package com.js.driver.model.bean;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/22
 * desc   :
 * version: 3.0.0
 */
public class AuthInfo {

    /**
     * personName : hzb
     * idCode : 123456
     * idImage : xxx.png
     * idBackImage : xxx.png
     * idHandImage : xxx.png
     * address : 123
     * driverImage : xxx.png
     * driverLevel : C4
     * auditRemark : 拒绝
     */

    private String personName;
    private String idCode;
    private String idImage;
    private String idBackImage;
    private String idHandImage;
    private String address;
    private String driverImage;
    private String driverLevel;
    private String auditRemark;

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

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getIdBackImage() {
        return idBackImage;
    }

    public void setIdBackImage(String idBackImage) {
        this.idBackImage = idBackImage;
    }

    public String getIdHandImage() {
        return idHandImage;
    }

    public void setIdHandImage(String idHandImage) {
        this.idHandImage = idHandImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }

    public String getDriverLevel() {
        return driverLevel;
    }

    public void setDriverLevel(String driverLevel) {
        this.driverLevel = driverLevel;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }
}
