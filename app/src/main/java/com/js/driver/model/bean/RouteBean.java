package com.js.driver.model.bean;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/11
 * desc   :
 * version: 3.0.0
 */
public class RouteBean {

    /**
     * id : 12
     * state : 0  //状态，0未启用，1启用
     * cphm :
     * driverPhone : 15737936517
     * classic : 2  //是否精品路线，1是，0否，2审核中
     * carLengthName : 1.5米
     * startAddressCodeName : 上海市
     * carLength : 1.5
     * carModel : 1
     * carModelName : 卡车
     * subscriberId : 2
     * remark : 简介
     * arriveAddressCodeName : 上海市上海市普陀区
     * driverName : 1
     * arriveAddressCode : 310107
     * startAddressCode : 310000
     */

    private int id;
    private int state;
    private String cphm;
    private String driverPhone;
    private int classic;
    private String carLengthName;
    private String startAddressCodeName;
    private String carLength;
    private String carModel;
    private String carModelName;
    private int subscriberId;
    private String remark;
    private String arriveAddressCodeName;
    private String driverName;
    private String arriveAddressCode;
    private String startAddressCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public int getClassic() {
        return classic;
    }

    public void setClassic(int classic) {
        this.classic = classic;
    }

    public String getCarLengthName() {
        return carLengthName;
    }

    public void setCarLengthName(String carLengthName) {
        this.carLengthName = carLengthName;
    }

    public String getStartAddressCodeName() {
        return startAddressCodeName;
    }

    public void setStartAddressCodeName(String startAddressCodeName) {
        this.startAddressCodeName = startAddressCodeName;
    }

    public String getCarLength() {
        return carLength;
    }

    public void setCarLength(String carLength) {
        this.carLength = carLength;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getArriveAddressCodeName() {
        return arriveAddressCodeName;
    }

    public void setArriveAddressCodeName(String arriveAddressCodeName) {
        this.arriveAddressCodeName = arriveAddressCodeName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getArriveAddressCode() {
        return arriveAddressCode;
    }

    public void setArriveAddressCode(String arriveAddressCode) {
        this.arriveAddressCode = arriveAddressCode;
    }

    public String getStartAddressCode() {
        return startAddressCode;
    }

    public void setStartAddressCode(String startAddressCode) {
        this.startAddressCode = startAddressCode;
    }
}
