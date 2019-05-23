package com.js.driver.global;

/**
 * Created by huyg on 2019/4/21.
 */
public class Const {

    public static final int AUTH_CARD = 1;
    public static final int AUTH_BODY = 2;
    public static final int AUTH_DRIVER_CARD = 3;
    public static final int AUTH_BUSINESS_LICENSE = 4;
    public static final int UPLOAD_HEADIMG = 5;

    public static final String AuthStateStr[] = {"未提交", "认证中", "已认证", "认证失败"};
    public static final String AuthStateColor[] = {"#B4B4B4", "#0091FF", "#ECA73F", "#E02020"};
}
