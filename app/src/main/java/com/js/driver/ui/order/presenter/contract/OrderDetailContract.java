package com.js.driver.ui.order.presenter.contract;

import com.js.driver.model.bean.OrderBean;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

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

    }

    interface Presenter extends IPresenter<View> {
        void getOrderDetail(long id);

        void receiveOrder(long id);

        void refuseOrder(long id);

        void confirmOrder(long id);

        void cancelConfirmOrder(long id);
    }
}
