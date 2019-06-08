package com.js.driver.model.bean;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/08
 * desc   :
 * version: 3.0.0
 */
public class AccountInfo {

    /**
     * driverDepositState : 1
     * subscriberId : 2
     * id : 2
     * balanceState : 2
     * driverDeposit : 0
     * tradeDeposit : 0
     * balance : 0.01
     * consignorDeposit : 0
     */

    private int driverDepositState;
    private int subscriberId;
    private int id;
    private int balanceState;
    private int driverDeposit;
    private int tradeDeposit;
    private double balance;
    private int consignorDeposit;

    public int getDriverDepositState() {
        return driverDepositState;
    }

    public void setDriverDepositState(int driverDepositState) {
        this.driverDepositState = driverDepositState;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalanceState() {
        return balanceState;
    }

    public void setBalanceState(int balanceState) {
        this.balanceState = balanceState;
    }

    public int getDriverDeposit() {
        return driverDeposit;
    }

    public void setDriverDeposit(int driverDeposit) {
        this.driverDeposit = driverDeposit;
    }

    public int getTradeDeposit() {
        return tradeDeposit;
    }

    public void setTradeDeposit(int tradeDeposit) {
        this.tradeDeposit = tradeDeposit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getConsignorDeposit() {
        return consignorDeposit;
    }

    public void setConsignorDeposit(int consignorDeposit) {
        this.consignorDeposit = consignorDeposit;
    }
}
