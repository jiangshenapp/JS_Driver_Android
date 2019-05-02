package com.js.driver.ui.user.presenter;

import com.js.driver.api.UserApi;
import com.js.driver.ui.user.presenter.contract.ResetPwdContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;
import com.xlgcx.http.BaseHttpResponse;
import com.xlgcx.http.rx.RxException;
import com.xlgcx.http.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/02
 * desc   :
 * version: 3.0.0
 */
public class ResetPwdPresenter extends RxPresenter<ResetPwdContract.View> implements ResetPwdContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public ResetPwdPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void resetPwd(String phone, String pwd) {
        Disposable disposable = mApiFactory.getApi(UserApi.class)
                .resetPwdStep2(phone, pwd)
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
                            mView.onResetPwd();
                        }
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }
}
