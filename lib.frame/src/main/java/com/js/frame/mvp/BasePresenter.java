package com.js.frame.mvp;

/**
 * Created by huyg on 2018/8/22.
 */

public class BasePresenter<T extends IBaseView> implements IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
