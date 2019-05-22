package com.xlgcx.http.interceptor;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huyg on 2019-05-22.
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        MediaType mediaType = request.body().contentType();
        try {
            Field field = mediaType.getClass().getDeclaredField("mediaType");
            field.setAccessible(true);
            field.set(mediaType, "application/json");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return chain.proceed(request);
    }
}
