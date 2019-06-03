package com.js.driver.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huyg on 2019-06-03.
 */
public class WxPayBean {

    /**
     * package : Sign=WXPay
     * appid : wxbba5c5b208ed8f31
     * sign : 8597AC443D3E121E790485510444C745
     * partnerid : 1533708401
     * prepayid : wx031542083464128a1a30a0140692633500
     * noncestr : 1559547728370
     * timestamp : 1559547728
     */

    @SerializedName("package")
    private String packageX;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
