package com.js.driver.ui.center.presenter;

import com.js.driver.ui.center.presenter.contract.FeedBackContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import javax.inject.Inject;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/22
 * desc   :
 * version: 3.0.0
 */
public class FeedBackPresenter extends RxPresenter<FeedBackContract.View> implements FeedBackContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public FeedBackPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }
}
