package com.xlgcx.frame.mvp;

/**
 * Created by huyg on 2018/8/22.
 */

public interface IBaseView {
    void showProgress();

    void closeProgress();

    void toast(String message);

}
