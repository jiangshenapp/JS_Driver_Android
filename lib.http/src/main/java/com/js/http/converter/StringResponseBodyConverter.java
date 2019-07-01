package com.xlgcx.http.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by huyg on 2018/7/12.
 */
public class StringResponseBodyConverter implements Converter<ResponseBody,String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}
