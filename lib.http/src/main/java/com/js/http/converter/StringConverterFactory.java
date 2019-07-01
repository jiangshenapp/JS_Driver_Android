package com.xlgcx.http.converter;


import com.xlgcx.http.TypeString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by huyg on 2018/7/12.
 */
public class StringConverterFactory extends Converter.Factory{

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    private StringConverterFactory() {

    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        if (!(type instanceof Class<?>)) {
            return null;
        }

        for( Annotation annotation :annotations) {
            if( annotation instanceof TypeString) {
                return new StringResponseBodyConverter();
            }
        }

        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (!(type instanceof Class<?>)) {
            return null;
        }
        for( Annotation annotation :parameterAnnotations) {
            if( annotation instanceof TypeString) {
                return new StringRequestBodyConverter();
            }
        }
        return null;
    }

}
