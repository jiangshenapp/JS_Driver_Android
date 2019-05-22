package com.js.driver.ui.user.presenter;

import com.js.driver.api.UserApi;
import com.js.driver.model.bean.AuthInfo;
import com.js.driver.model.bean.UserInfo;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.user.presenter.contract.DriverVerifiedContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/24.
 */
public class DriverVerifiedPresenter extends RxPresenter<DriverVerifiedContract.View>  implements DriverVerifiedContract.Presenter{

    private ApiFactory mApiFactory;

    @Inject
    public DriverVerifiedPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void getAuthInfo() {
        Disposable disposable = mApiFactory.getApi(UserApi.class)
                .getDriverVerifiedInfo()
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<AuthInfo>() {
                    @Override
                    public void accept(AuthInfo authInfo) throws Exception {
                        mView.onAuthInfo(authInfo);
                    }
                }, new RxException<>(e -> {
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }
}
