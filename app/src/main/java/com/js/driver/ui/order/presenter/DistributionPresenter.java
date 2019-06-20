package com.js.driver.ui.order.presenter;

import com.js.driver.ui.order.presenter.contract.DistributionContract;
import com.xlgcx.frame.mvp.RxPresenter;

import javax.inject.Inject;

/**
 * Created by huyg on 2019-06-19.
 */
public class DistributionPresenter extends RxPresenter<DistributionContract.View> implements DistributionContract.Presenter{


    @Inject
    public DistributionPresenter(){

    }
}
