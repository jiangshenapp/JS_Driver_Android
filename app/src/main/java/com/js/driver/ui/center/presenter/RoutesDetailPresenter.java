package com.js.driver.ui.center.presenter;

import com.js.driver.api.RouteApi;
import com.js.driver.model.bean.RouteBean;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.center.presenter.contract.RoutesDetailContract;
import com.js.frame.mvp.RxPresenter;
import com.js.http.ApiFactory;
import com.js.http.BaseHttpResponse;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/12
 * desc   :
 * version: 3.0.0
 */
public class RoutesDetailPresenter extends RxPresenter<RoutesDetailContract.View> implements RoutesDetailContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public RoutesDetailPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void getRouteDetail(long id) {
        Disposable disposable = mApiFactory.getApi(RouteApi.class)
                .getRouteDetail(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<RouteBean>() {
                    @Override
                    public void accept(RouteBean routeBean) throws Exception {
                        mView.onRouteDetail(routeBean);
                    }
                }, new RxException<>(e -> {
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void applyClassicLine(long id) {
        Disposable disposable = mApiFactory.getApi(RouteApi.class)
                .applyClassicLine(id)
                .compose(RxSchedulers.io_main())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<BaseHttpResponse>() {
                    @Override
                    public void accept(BaseHttpResponse response) throws Exception {
                        mView.closeProgress();
                        if (response.isSuccess()){
                            mView.onApplyClassicLine();
                        } else {
                            mView.toast(response.getMsg());
                        }
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void enableLine(long lineId, long enable) {
        Disposable disposable = mApiFactory.getApi(RouteApi.class)
                .enableLine(lineId, enable)
                .compose(RxSchedulers.io_main())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<BaseHttpResponse>() {
                    @Override
                    public void accept(BaseHttpResponse response) throws Exception {
                        mView.closeProgress();
                        if (response.isSuccess()){
                            mView.onEnableLine();
                        } else {
                            mView.toast(response.getMsg());
                        }
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }
}
