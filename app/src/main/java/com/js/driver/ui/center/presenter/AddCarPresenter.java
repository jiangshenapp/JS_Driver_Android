package com.js.driver.ui.center.presenter;

import com.js.driver.api.CarApi;
import com.js.driver.model.bean.CarBean;
import com.js.driver.model.request.CarRequest;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.center.presenter.contract.AddCarContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;
import com.xlgcx.http.BaseHttpResponse;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/29.
 */
public class AddCarPresenter extends RxPresenter<AddCarContract.View> implements AddCarContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public AddCarPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void getCarDetail(long id) {
        Disposable disposable = mApiFactory.getApi(CarApi.class)
                .getCarDetail(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<CarBean>() {
                    @Override
                    public void accept(CarBean carBean) throws Exception {
                        mView.onCarDetail(carBean);
                    }
                }, new RxException<>(e -> {
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void bindingCar(String image1, String carModelId, String image2, String capacityVolume, String state, String carLengthId, String cphm, String capacityTonnage, String tradingNo, String transportNo) {
        Disposable disposable = mApiFactory.getApi(CarApi.class)
                .bindingCar(new CarRequest(image1, carModelId, image2, capacityVolume, state, carLengthId, cphm, capacityTonnage, tradingNo, transportNo))
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
                            mView.onBindingCar();
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
    public void unbindingCar(long id) {
        Disposable disposable = mApiFactory.getApi(CarApi.class)
                .unbindingCar(id)
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
                            mView.onUnbindingCar();
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
    public void reBindingCar(long id, String image1, String carModelId, String image2, String capacityVolume, String state, String carLengthId, String cphm, String capacityTonnage, String tradingNo, String transportNo) {
        Disposable disposable = mApiFactory.getApi(CarApi.class)
                .reBindingCar(id, new CarRequest(id, image1, carModelId, image2, capacityVolume, state, carLengthId, cphm, capacityTonnage, tradingNo, transportNo))
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
                            mView.onReBindingCar();
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
