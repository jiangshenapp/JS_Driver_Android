package com.js.driver.ui.center.presenter;

import com.js.driver.api.RouteApi;
import com.js.driver.model.request.RouteRequest;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.center.presenter.contract.AddRouteContract;
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
public class AddRoutePresenter extends RxPresenter<AddRouteContract.View> implements AddRouteContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public AddRoutePresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }


    @Override
    public void addLine(String arriveAddressCode, String carLength, String carModel, String startAddressCode, String remark) {
        Disposable disposable = mApiFactory.getApi(RouteApi.class)
                .addLine(new RouteRequest(arriveAddressCode, carLength, carModel, startAddressCode, remark))
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
                            mView.onAddLine();
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
    public void editLine(String arriveAddressCode, String carLength, String carModel, String startAddressCode, String remark, int id, int state, int classic) {
        Disposable disposable = mApiFactory.getApi(RouteApi.class)
                .editLine(new RouteRequest(arriveAddressCode, carLength, carModel, startAddressCode, remark, id, state, classic))
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
                            mView.onEditLine();
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
