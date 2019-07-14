package com.js.driver.ui.order.presenter;

import com.js.driver.api.OrderApi;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.request.OrderComment;
import com.js.driver.model.request.OrderDistribution;
import com.js.driver.rx.RxException;
import com.js.driver.rx.RxResult;
import com.js.driver.rx.RxSchedulers;
import com.js.driver.ui.order.presenter.contract.OrderDetailContract;
import com.js.frame.mvp.RxPresenter;
import com.js.http.ApiFactory;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderDetailPresenter extends RxPresenter<OrderDetailContract.View> implements OrderDetailContract.Presenter {

    private ApiFactory mApiFactory;

    @Inject
    public OrderDetailPresenter(ApiFactory apiFactory) {
        this.mApiFactory = apiFactory;
    }

    @Override
    public void getOrderDetail(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).getOrderDetail(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .subscribe(new Consumer<OrderBean>() {
                    @Override
                    public void accept(OrderBean orderBean) throws Exception {
                        mView.finishRefresh();
                        mView.onOrderDetail(orderBean);
                    }
                }, new RxException<>(e -> {
                    mView.finishRefresh();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void receiveOrder(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).receive(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onReceiveOrder(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void refuseOrder(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).refuse(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onRefuseOrder(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void confirmOrder(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).confirmOrder(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onConfirmOrder(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void cancelConfirmOrder(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).cancelConfirmOrder(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onCancelConfirmOrder(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void distribution(long id, OrderDistribution orderDistribution) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).distribution(id, orderDistribution)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onDistribution(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void cancelDistribution(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).cancelDistribution(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onCancelDistribution(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void completeDistribution(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).completeDistribution(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onCompleteDistribution(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void cancelReceive(long id) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).cancelReceive(id)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onCancelReceive(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }

    @Override
    public void commentOrder(OrderComment orderComment) {
        Disposable disposable = mApiFactory.getApi(OrderApi.class).commentOrder(orderComment)
                .compose(RxSchedulers.io_main())
                .compose(RxResult.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.closeProgress();
                        mView.onCommentOrder(aBoolean);
                    }
                }, new RxException<>(e -> {
                    mView.closeProgress();
                    mView.toast(e.getMessage());
                }));
        addDispose(disposable);
    }
}
