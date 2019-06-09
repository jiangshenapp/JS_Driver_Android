package com.js.driver.ui.center.presenter.contract;

import com.js.driver.model.bean.CarBean;
import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.response.ListResponse;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/29.
 */
public interface DriversContract {

    interface View extends IBaseView {
        void onDriverList(ListResponse<DriverBean> response);

        void finishRefreshAndLoadMore();
    }

    interface Presenter extends IPresenter<View> {
        void getDriverList();
    }
}
