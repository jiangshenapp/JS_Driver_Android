package com.js.driver.ui.order.presenter;

import com.js.driver.ui.order.presenter.contract.OrderContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import javax.inject.Inject;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderPresenter extends RxPresenter<OrderContract.View> implements OrderContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public OrderPresenter(){

    }

}
