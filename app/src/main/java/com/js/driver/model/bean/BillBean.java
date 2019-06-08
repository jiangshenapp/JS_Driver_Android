package com.js.driver.model.bean;

/**
 * Created by huyg on 2019/4/24.
 */
public class BillBean {

    /**
     * remark : 余额充值
     * accountId : 2
     * finishTime :
     * id : 49
     * tradeType : 1
     * orderNo :
     * createTime : 2019-06-08 18:24:40
     * tradeMoney : 0.01
     * tradeNo : CZ-1559989480437
     * state : 0
     * operateBy :
     */

    private String remark;
    private int accountId;
    private String finishTime;
    private int id;
    private int tradeType;
    private String orderNo;
    private String createTime;
    private double tradeMoney;
    private String tradeNo;
    private int state;
    private String operateBy;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(double tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy;
    }
}
