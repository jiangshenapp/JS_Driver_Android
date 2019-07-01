package com.js.driver.ui.user.presenter.contract;

import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/02
 * desc   : 忘记密码第二步
 * version: 3.0.0
 */
public interface ResetPwdContract {

    interface View extends IBaseView {
        void onResetPwd();
    }

    interface Presenter extends IPresenter<View> {
        void resetPwd(String phone, String pwd);
    }
}
