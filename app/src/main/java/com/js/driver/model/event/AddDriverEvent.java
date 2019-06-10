package com.js.driver.model.event;

/**
 * Created by huyg on 2019/4/29.
 */
public class AddDriverEvent {

    public int type; //1、根据手机号获取司机信息；2、绑定司机
    public int driverId;
    public String driverPhone;

    public AddDriverEvent(int type, String driverPhone) {
        this.type = type;
        this.driverPhone = driverPhone;
    }

    public AddDriverEvent(int type, int driverId) {
        this.type = type;
        this.driverId = driverId;
    }
}
