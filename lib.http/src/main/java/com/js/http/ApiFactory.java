package com.xlgcx.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huyg on 2018/4/24.
 */

public class ApiFactory {

    private Map<String, Object> retrofitMap;
    private RetrofitClient retrofitClient;

    public ApiFactory() {
        retrofitMap = new HashMap<>();
        retrofitClient = RetrofitClient.getInstance();
    }


    public <T> T getApi(Class<T> retrofitClass) {
        T result = null;
        if (retrofitMap != null) {
            synchronized (retrofitMap) {
                result = (T) retrofitMap.get(retrofitClass.getName());
                if (result == null) {
                    result = retrofitClient.create(retrofitClass);
                    retrofitMap.put(retrofitClass.getName(), result);
                }
            }
        }
        return result;
    }


}
