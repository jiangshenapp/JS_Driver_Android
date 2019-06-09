package com.js.driver.ui.center.presenter.contract;

import com.js.driver.model.bean.CarBean;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.response.ListResponse;
import com.js.driver.ui.order.presenter.contract.OrderContract;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/29.
 */
public interface CarsContract {

    interface View extends IBaseView {
        void onCarList(ListResponse<CarBean> response);

        void finishRefreshAndLoadMore();
    }

    interface Presenter extends IPresenter<View> {
        void getCarList();
    }
}
