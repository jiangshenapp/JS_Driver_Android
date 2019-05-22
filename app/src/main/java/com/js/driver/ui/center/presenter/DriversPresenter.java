package com.js.driver.ui.center.presenter;

import com.js.driver.ui.center.presenter.contract.DriversContract;
import com.xlgcx.frame.mvp.RxPresenter;

import javax.inject.Inject;

/**
 * Created by huyg on 2019/4/29.
 */
public class DriversPresenter extends RxPresenter<DriversContract.View> implements DriversContract.Presenter {

    @Inject
    public DriversPresenter(){

    }
}
