package com.xlgcx.http;

/**
 * Created by huyg on 2018/9/25.
 */

public class HttpResponse<T> extends BaseHttpResponse{


    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
