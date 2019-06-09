package com.js.driver.ui.center.presenter;

import com.js.driver.api.CarApi;
import com.js.driver.api.DriverApi;
import com.js.driver.model.bean.CarBean;
import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.response.ListResponse;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.center.presenter.contract.DriversContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

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
}
