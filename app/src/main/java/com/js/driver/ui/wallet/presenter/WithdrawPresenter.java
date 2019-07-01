package com.js.driver.ui.wallet.presenter;

import com.js.driver.api.PayApi;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.wallet.presenter.contract.WithdrawContract;
import com.js.frame.mvp.RxPresenter;
import com.js.http.ApiFactory;
import com.js.http.BaseHttpResponse;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/24.
 */
public class WithdrawPresenter extends RxPresenter<WithdrawContract.View> implements WithdrawContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public WithdrawPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void balanceWithdraw(int withdrawType, int withdrawChannel, String bankCard, String khh, String zh, String zfbzh, String zfbmc) {
        Disposable disposable = mApiFactory.getApi(PayApi.class)
                .balanceWithdraw(withdrawType, withdrawChannel, bankCard, khh, zh, zfbzh, zfbmc)
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
                            mView.onBalanceWithdraw();
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
