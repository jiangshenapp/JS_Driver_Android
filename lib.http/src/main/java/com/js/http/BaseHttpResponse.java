package com.js.http;

/**
 * Created by huyg on 2018/9/25.
 */

public class BaseHttpResponse {
    private int code;
    private String msg;
    public static int SUCCESS = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int ret) {
        this.code = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return SUCCESS == code;
    }


}
