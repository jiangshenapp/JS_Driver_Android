package com.js.driver.ui.center.presenter;

import com.js.driver.ui.center.presenter.contract.AddCarContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import javax.inject.Inject;

/**
 * Created by huyg on 2019/4/29.
 */
public class AddCarPresenter extends RxPresenter<AddCarContract.View> implements AddCarContract.Presenter {

    @Inject
    public AddCarPresenter(){

    }
}
