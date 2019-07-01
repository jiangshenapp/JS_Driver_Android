package com.js.driver.presenter;


import com.js.driver.presenter.contract.SplashContract;
import com.js.frame.mvp.RxPresenter;

import javax.inject.Inject;

/**
 * Created by huyg on 2019-05-23.
 */
public class  SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {



    @Inject
    public SplashPresenter() {

    }


}
