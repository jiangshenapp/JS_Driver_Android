package com.js.driver.ui.user.presenter.contract;

import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/04/25
 * desc   :
 * version: 3.0.0
 */
public interface RegisterContract {

    interface View extends IBaseView {
        void onRegister();
    }

    interface Presenter extends IPresenter<View> {

        void register(String phone, String password, String code);
    }
}
