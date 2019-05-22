package com.js.driver.ui.center.presenter;

import com.js.driver.ui.center.presenter.contract.CarsContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import javax.inject.Inject;

/**
 * Created by huyg on 2019/4/29.
 */
public class CarsPresenter extends RxPresenter<CarsContract.View> implements CarsContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public CarsPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

}
