package com.js.driver.ui.center.presenter;

import com.js.driver.api.DriverApi;
import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.response.ListResponse;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.center.presenter.contract.DriversContract;
import com.js.frame.mvp.RxPresenter;
import com.js.http.ApiFactory;
import com.js.http.BaseHttpResponse;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/29.
 */
public class DriversPresenter extends RxPresenter<DriversContract.View> implements DriversContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public DriversPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void getDriverList() {
        Disposable disposable = mApiFactory.getApi(DriverApi.class).getDriverList()
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<ListResponse<DriverBean>>() {
                    @Override
                    public void accept(ListResponse<DriverBean> response) throws Exception {
                        mView.finishRefreshAndLoadMore();
                        mView.onDriverList(response);
                    }
                },new RxException<>(e->{
                    mView.finishRefreshAndLoadMore();
                }));
        addDispose(disposable);
    }

    @Override
    public void findDriverByMobile(String mobile) {
        Disposable disposable = mApiFactory.getApi(DriverApi.class)
                .findDriverByMobile(mobile)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<DriverBean>() {
                    @Override
                    public void accept(DriverBean driverBean) throws Exception {
                        mView.onFindDriverByMobile(driverBean);
                    }
                }, new RxException<>(e -> {
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void bindingDriver(long driverId) {
        Disposable disposable = mApiFactory.getApi(DriverApi.class)
                .bindingDriver(driverId)
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
                            mView.onBindingDriver();
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
    public void unbindingDriver(long driverId) {
        Disposable disposable = mApiFactory.getApi(DriverApi.class)
                .unbindingDriver(driverId)
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
                            mView.onUnbindingDriver();
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
