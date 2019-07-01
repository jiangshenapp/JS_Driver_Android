package com.js.driver.ui.center.presenter.contract;

import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/12
 * desc   :
 * version: 3.0.0
 */
public interface AddRouteContract {

    interface View extends IBaseView {
        void onAddLine();
        void onEditLine();
    }

    interface Presenter extends IPresenter<View> {
        void addLine(String arriveAddressCode, String carLength, String carModel,
                     String startAddressCode, String remark);
        void editLine(String arriveAddressCode, String carLength, String carModel,
                      String startAddressCode, String remark, int id, int state, int classic);
    }
}
