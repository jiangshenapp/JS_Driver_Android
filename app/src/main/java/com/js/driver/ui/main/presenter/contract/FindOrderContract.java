package com.js.driver.ui.main.presenter.contract;

import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.response.ListResponse;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/1.
 */
public interface FindOrderContract {

    interface View extends IBaseView {
        void onFindOrders(ListResponse<OrderBean> response);
        void finishRefreshAndLoadMore();
    }

    interface Presenter extends IPresenter<View> {
        void findOrders(int current, int size, String startAddress, String arriveAddress);
    }
}
