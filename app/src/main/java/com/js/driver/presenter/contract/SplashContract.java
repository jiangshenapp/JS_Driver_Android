package com.js.driver.presenter.contract;


import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019-05-23.
 */
public interface SplashContract {

    interface View extends IBaseView {

    }

    interface Presenter extends IPresenter<View> {

    }
}
