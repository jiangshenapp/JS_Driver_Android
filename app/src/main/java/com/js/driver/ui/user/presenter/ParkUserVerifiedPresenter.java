package com.js.driver.ui.user.presenter;

import com.js.driver.api.UserApi;
import com.js.driver.model.bean.AuthInfo;
import com.js.driver.model.request.ParkVerifiedRequest;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.user.presenter.contract.ParkUserVerifiedContract;
import com.js.frame.mvp.RxPresenter;
import com.js.http.ApiFactory;
import com.js.http.BaseHttpResponse;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/24.
 */
public class ParkUserVerifiedPresenter extends RxPresenter<ParkUserVerifiedContract.View>  implements ParkUserVerifiedContract.Presenter{

    private ApiFactory mApiFactory;

    @Inject
    public ParkUserVerifiedPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void getParkUserVerifiedInfo() {
        Disposable disposable = mApiFactory.getApi(UserApi.class)
                .getParkVerifiedInfo()
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<AuthInfo>() {
                    @Override
                    public void accept(AuthInfo authInfo) throws Exception {
                        mView.onParkUserVerifiedInfo(authInfo);
                    }
                }, new RxException<>(e -> {
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void submitParkUserVerified(String companyName, String companyType, String registrationNumber, String address, String detailAddress, String businessLicenceImage) {
        Disposable disposable = mApiFactory.getApi(UserApi.class)
                .parkVerified(new ParkVerifiedRequest(companyName, companyType, registrationNumber, address, detailAddress, businessLicenceImage))
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
                            mView.onSubmitParkUserVerified();
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
