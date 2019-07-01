package com.xlgcx.frame.mvp;


/**
 * Created by huyg on 2018/8/22.
 */

public interface IPresenter<T extends IBaseView> {

    void attachView(T view);

    void detachView();
}
