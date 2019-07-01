package com.js.driver.ui.order.presenter.contract;

import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.request.OrderComment;
import com.js.driver.model.request.OrderDistribution;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/29.
 */
public interface OrderDetailContract {

    interface View extends IBaseView {
        void onOrderDetail(OrderBean orderBean);

        void finishRefresh();

        void onReceiveOrder(boolean isOk);

        void onRefuseOrder(boolean isOk);

        void onConfirmOrder(boolean isOk);

        void onCancelConfirmOrder(boolean isOk);

        void onDistribution(boolean isOk);

        void onCancelDistribution(boolean isOk);

        void onCompleteDistribution(boolean isOk);

        void onCancelReceive(boolean isOk);

        void onCommentOrder(boolean isOk);

    }

    interface Presenter extends IPresenter<View> {
        void getOrderDetail(long id);

        void receiveOrder(long id);

        void refuseOrder(long id);

        void confirmOrder(long id);

        void cancelConfirmOrder(long id);

        void distribution(long id, OrderDistribution orderDistribution);

        void cancelDistribution(long id);

        void completeDistribution(long id);

        void cancelReceive(long id);

        void commentOrder(OrderComment orderComment);
    }
}
