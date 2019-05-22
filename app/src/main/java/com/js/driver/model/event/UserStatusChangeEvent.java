package com.js.driver.model.event;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/22
 * desc   : 用户状态改变事件
 * version: 3.0.0
 */
public class UserStatusChangeEvent {

    // 1：登录成功 2：退出成功 3：用户修改信息
    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGOUT_SUCCESS = 2;
    public static final int CHANGE_SUCCESS = 3;

    public int index;

    public UserStatusChangeEvent(int index) {
        this.index = index;
    }
}
