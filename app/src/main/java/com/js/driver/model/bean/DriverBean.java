package com.js.driver.model.bean;

/**
 * Created by huyg on 2019/4/29.
 */
public class DriverBean {

    /**
     * parkId : 2
     * driverName : 1
     * avatar : 01c5d81e06c441529806c4aaa86b300c.png
     * id : 2
     * driverId : 2
     * driverPhone : 15737936517
     * driverLevel : A1
     */

    private int parkId;
    private String driverName;
    private String avatar;
    private int id;
    private int driverId;
    private String driverPhone;
    private String driverLevel;

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverLevel() {
        return driverLevel;
    }

    public void setDriverLevel(String driverLevel) {
        this.driverLevel = driverLevel;
    }
}
