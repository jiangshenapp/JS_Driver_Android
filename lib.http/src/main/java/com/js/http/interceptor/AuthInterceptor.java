package com.js.http.interceptor;

import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.js.http.BaseHttpResponse;
import com.js.http.HttpApp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by huyg on 2019/4/29.
 */
public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();//获取请求
        Request tokenRequest = null;
        tokenRequest = originalRequest.newBuilder()//往请求头中添加 token 字段
                .header("token", HttpApp.getApp().token)
                .build();
        Response response = chain.proceed(tokenRequest);
        String body = response.body().string();
        if (!TextUtils.isEmpty(body)) {
            BaseHttpResponse baseHttpResponse = new Gson().fromJson(body, BaseHttpResponse.class);
            if (baseHttpResponse.getCode() == -1) {
                ARouter.getInstance().build("/user/login").navigation();
                return response.newBuilder()
                        .body(ResponseBody.create(MediaType.parse("UTF-8"), body))
                        .build();
            }
        }
        //body只能读取一次
        return response.newBuilder()
                .body(ResponseBody.create(MediaType.parse("UTF-8"), body))
                .build();
    }
}
