package com.js.driver.ui.center.presenter.contract;

import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.bean.RouteBean;
import com.js.driver.model.response.ListResponse;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/11
 * desc   :
 * version: 3.0.0
 */
public interface RoutesContract {

    interface View extends IBaseView {
        void onRouteList(ListResponse<RouteBean> response);
        void finishRefreshAndLoadMore();
        void onRemoveRoute();
    }

    interface Presenter extends IPresenter<View> {
        void getRouteList();
        void removeRoute(long id);
    }
}
