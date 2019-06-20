package com.js.driver.ui.main.presenter;

import com.js.driver.api.OrderApi;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.request.FindOrder;
import com.js.driver.model.request.LineAppFind;
import com.js.driver.model.response.ListResponse;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.main.presenter.contract.FindOrderContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/1.
 */
public class FindOrderPresenter extends RxPresenter<FindOrderContract.View> implements FindOrderContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public FindOrderPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void findOrders(int current, int size, LineAppFind lineAppFind) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).find(current,
                size,
                lineAppFind)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<ListResponse<OrderBean>>() {
                    @Override
                    public void accept(ListResponse<OrderBean> response) throws Exception {
                        mView.finishRefreshAndLoadMore();
                        mView.onFindOrders(response);
                    }
                }, new RxException<>(e -> {
                    mView.toast(e.getMessage());
                    mView.finishRefreshAndLoadMore();
                }));
        addDispose(disposable);
    }
}
