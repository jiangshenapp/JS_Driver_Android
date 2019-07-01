package com.js.driver.ui.center.presenter.contract;

import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.response.ListResponse;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/29.
 */
public interface DriversContract {

    interface View extends IBaseView {
        void onDriverList(ListResponse<DriverBean> response);
        void finishRefreshAndLoadMore();
        void onFindDriverByMobile(DriverBean driverBean);
        void onBindingDriver();
        void onUnbindingDriver();
    }

    interface Presenter extends IPresenter<View> {
        void getDriverList();
        void findDriverByMobile(String mobile);
        void bindingDriver(long driverId);
        void unbindingDriver(long driverId);
    }
}
