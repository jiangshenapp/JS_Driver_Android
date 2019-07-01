package com.js.driver.ui.order.presenter.contract;

import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.response.ListResponse;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/29.
 */
public interface OrderContract {

    interface View extends IBaseView {
        void onOrderList(ListResponse<OrderBean> response);

        void finishRefreshAndLoadMore();
    }

    interface Presenter extends IPresenter<View> {
        void getOrderList(int current, int size, int status);
    }
}
