package com.js.driver.ui.main.presenter;

import android.text.TextUtils;

import com.js.driver.App;
import com.js.driver.api.UserApi;
import com.js.driver.model.bean.UserInfo;
import com.js.driver.ui.main.presenter.contract.MineContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/1.
 */
public class MinePresenter extends RxPresenter<MineContract.View> implements MineContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public MinePresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void getUserInfo() {
        if (!TextUtils.isEmpty(App.getInstance().token)) {
            Disposable disposable = mApiFactory.getApi(UserApi.class)
                    .profile()
                    .compose(RxSchedulers.io_main())
                    .compose(RxResult.handleResult())
                    .subscribe(new Consumer<UserInfo>() {
                        @Override
                        public void accept(UserInfo userInfo) throws Exception {
                            mView.finishRefresh();
                            mView.onUserInfo(userInfo);
                        }
                    }, new RxException<>(e -> {
                        mView.finishRefresh();
                        mView.toast(e.getMessage());
                    }));
            addDispose(disposable);
        }
    }
}
