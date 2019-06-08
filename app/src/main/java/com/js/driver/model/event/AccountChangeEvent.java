package com.js.driver.model.event;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/08
 * desc   : 钱包改变
 * version: 3.0.0
 */
public class AccountChangeEvent {

    // 1：钱包改变
    public static final int WALLET_CHANGE = 1;

    public int index;

    public AccountChangeEvent(int index) {
        this.index = index;
    }
}
