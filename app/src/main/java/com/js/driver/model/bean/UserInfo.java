package com.js.driver.model.bean;

/**
 * Created by huyg on 2019/4/29.
 */
public class UserInfo {

    /**
     * id : 5
     * mobile : 13429323525
     * nickName : null
     * password : 670b14728ad9902aecba32e22fa4f6bd
     * driverVerified : 0
     * parkVerified : 0
     * companyConsignorVerified : 0
     * personConsignorVerified : 0
     * createTime : 2019-04-29 14:35:02
     * lastPosition : null
     * lastPositionTime : null
     */

    private int id;
    private String mobile;
    private String nickName;
    private String avatar;
    private String password;
    private int driverVerified;
    private int parkVerified;
    private int companyConsignorVerified;
    private int personConsignorVerified;
    private String createTime;
    private String lastPosition;
    private long lastPositionTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDriverVerified() {
        return driverVerified;
    }

    public void setDriverVerified(int driverVerified) {
        this.driverVerified = driverVerified;
    }

    public int getParkVerified() {
        return parkVerified;
    }

    public void setParkVerified(int parkVerified) {
        this.parkVerified = parkVerified;
    }

    public int getCompanyConsignorVerified() {
        return companyConsignorVerified;
    }

    public void setCompanyConsignorVerified(int companyConsignorVerified) {
        this.companyConsignorVerified = companyConsignorVerified;
    }

    public int getPersonConsignorVerified() {
        return personConsignorVerified;
    }

    public void setPersonConsignorVerified(int personConsignorVerified) {
        this.personConsignorVerified = personConsignorVerified;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(String lastPosition) {
        this.lastPosition = lastPosition;
    }

    public long getLastPositionTime() {
        return lastPositionTime;
    }

    public void setLastPositionTime(long lastPositionTime) {
        this.lastPositionTime = lastPositionTime;
    }
}
