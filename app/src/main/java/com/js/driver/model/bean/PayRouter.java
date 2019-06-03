package com.js.driver.model.bean;

/**
 * Created by huyg on 2019-05-31.
 */
public class PayRouter {


    /**
     * routeId : 1
     * merchantId : 1
     * defaultAccount : 1
     * channelId : 4
     * businessId : 1
     * channelType : 2
     * status : 1
     * createTime : 2019-05-30 17:54:30
     * updateTime : 2019-05-30 17:54:30
     * delFlag : 0
     * channelName : 微信APP支付
     * businessName : 运力端充值
     */

    private int routeId;
    private int merchantId;
    private int defaultAccount;
    private int channelId;
    private int businessId;
    private int channelType;
    private int status;
    private String createTime;
    private String updateTime;
    private String delFlag;
    private String channelName;
    private String businessName;
    private boolean checked;


    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(int defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
