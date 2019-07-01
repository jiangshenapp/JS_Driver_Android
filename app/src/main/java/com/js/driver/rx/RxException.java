package com.js.driver.rx;


import com.js.http.exception.NoNetworkException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2018/8/27.
 */

public class RxException<T extends Throwable> implements Consumer<T> {



    private static final String TAG = "RxException";

    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接异常";
    private static final String CONNECTEXCEPTION = "网络连接异常";
    private static final String UNKNOWNHOSTEXCEPTION = "网络异常";

    private Consumer<? super Throwable> onError;
    public RxException(Consumer<? super Throwable> onError) {
        this.onError=onError;
    }

    /**
     * Consume the given value.
     *
     * @param t the value
     * @throws Exception on error
     */
    @Override
    public void accept(T t) throws Exception {

        if (t instanceof SocketTimeoutException) {
            onError.accept(new Throwable(SOCKETTIMEOUTEXCEPTION));
        } else if (t instanceof ConnectException) {
            onError.accept(new Throwable(CONNECTEXCEPTION));
        } else if (t instanceof UnknownHostException) {
            onError.accept(new Throwable(UNKNOWNHOSTEXCEPTION));
        } else if (t instanceof NoNetworkException){
            onError.accept(new Throwable(UNKNOWNHOSTEXCEPTION));
        }
        else {
            onError.accept(t);
        }
    }
}
