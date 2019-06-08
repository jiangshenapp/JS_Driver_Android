package com.js.driver.ui.wallet.presenter;

import com.js.driver.api.OrderApi;
import com.js.driver.api.PayApi;
import com.js.driver.global.Const;
import com.js.driver.model.bean.BillBean;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.bean.PayRouter;
import com.js.driver.model.request.OrderStatus;
import com.js.driver.model.response.ListResponse;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.wallet.presenter.contract.BillContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/24.
 */
public class BillPresenter extends RxPresenter<BillContract.View> implements BillContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public BillPresenter(ApiFactory mApiFactory) {
        this.mApiFactory = mApiFactory;
    }

    @Override
    public void getBillList(int status) {
        Disposable disposable = mApiFactory.getApi(PayApi.class)
                .getTradeRecord()
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<List<BillBean>>() {
                    @Override
                    public void accept(List<BillBean> billBeans) throws Exception {
                        mView.closeProgress();
                        mView.finishRefreshAndLoadMore();
                        mView.onBillList(billBeans);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.finishRefreshAndLoadMore();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }
}
