package com.js.driver.ui.order.presenter;

import com.js.driver.api.OrderApi;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.request.OrderStatus;
import com.js.driver.model.response.ListResponse;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.order.presenter.contract.OrderContract;
import com.xlgcx.frame.mvp.RxPresenter;
import com.xlgcx.http.ApiFactory;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderPresenter extends RxPresenter<OrderContract.View> implements OrderContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public OrderPresenter(ApiFactory mApiFactory) {
        this.mApiFactory = mApiFactory;
    }


    @Override
    public void getOrderList(int current, int size, int status) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).getOrderList(current,size,new OrderStatus(size))
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<ListResponse<OrderBean>>() {
                    @Override
                    public void accept(ListResponse<OrderBean> response) throws Exception {
                        mView.finishRefreshAndLoadMore();
                        mView.onOrderList(response);
                    }
                },new RxException<>(e->{
                    mView.finishRefreshAndLoadMore();
                }));
        addDispose(disposable);
    }
}
