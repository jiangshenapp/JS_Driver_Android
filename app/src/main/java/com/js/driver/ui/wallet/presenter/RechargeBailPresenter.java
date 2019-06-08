package com.js.driver.ui.wallet.presenter;

import com.js.driver.api.PayApi;
import com.js.driver.global.Const;
import com.js.driver.model.bean.PayInfo;
import com.js.driver.model.bean.PayRouter;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.wallet.presenter.contract.RechargeBailContract;
import com.js.driver.ui.wallet.presenter.contract.RechargeContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/08
 * desc   :
 * version: 3.0.0
 */
public class RechargeBailPresenter extends RxPresenter<RechargeBailContract.View> implements RechargeBailContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public RechargeBailPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void payOrder(int channelType, double money, int routeId) {
        Disposable disposable = mApiFactory.getApi(PayApi.class)
                .recharge(Const.BUSINESS_ID, channelType, money, routeId)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<PayInfo>() {
                    @Override
                    public void accept(PayInfo payInfo) throws Exception {
                        mView.closeProgress();
                        mView.onPayOrder(payInfo);
                    }
                },new RxException<>(e->{
                    mView.toast(e.getMessage());
                    mView.closeProgress();
                }));
        addDispose(disposable);
    }

    @Override
    public void getPayRouter() {
        Disposable disposable = mApiFactory.getApi(PayApi.class)
                .getPayRouter(Const.BUSINESS_ID, Const.MERCHANT_ID)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<List<PayRouter>>() {
                    @Override
                    public void accept(List<PayRouter> payRouters) throws Exception {
                        mView.closeProgress();
                        mView.onPayRouter(payRouters);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }
}
