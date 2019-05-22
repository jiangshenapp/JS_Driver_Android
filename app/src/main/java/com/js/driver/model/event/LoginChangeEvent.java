package com.js.driver.model.event;

/**
 * Created by huyg on 2019/4/22.
 */
public class LoginChangeEvent {

    // 0：密码登录 1：验证码登录
    public int index;

    public LoginChangeEvent(int index) {
        this.index = index;
    }
}
