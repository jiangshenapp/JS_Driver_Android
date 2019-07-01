package com.js.driver.ui.user.presenter;

import com.js.driver.api.UserApi;
import com.js.driver.ui.user.presenter.contract.SmsCodeContract;
import com.js.frame.mvp.RxPresenter;
import com.js.http.ApiFactory;
import com.js.http.BaseHttpResponse;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/04/25
 * desc   :
 * version: 3.0.0
 */
public class SmsCodePresenter extends RxPresenter<SmsCodeContract.View> implements SmsCodeContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public SmsCodePresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void sendSmsCode(String phone) {
        Disposable disposable = mApiFactory.getApi(UserApi.class)
                .sendSmsCode(phone)
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
                        mView.toast(response.getMsg());
                        if (response.isSuccess()){
                            mView.onSmsCode();
                        }
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }
}
