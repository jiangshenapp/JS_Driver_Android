package com.js.driver.ui.user.presenter.contract;

import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/21.
 */
public interface PwdLoginContract {

    interface View extends IBaseView {
        void onLogin(String token);
    }

    interface Presenter extends IPresenter<View> {
        void login(String phone, String pwd);
    }
}
