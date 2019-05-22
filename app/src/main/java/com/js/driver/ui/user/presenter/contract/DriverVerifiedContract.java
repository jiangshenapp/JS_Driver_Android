package com.js.driver.ui.user.presenter.contract;

import com.js.driver.model.bean.AuthInfo;
import com.js.driver.model.bean.UserInfo;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/24.
 */
public interface DriverVerifiedContract {

    interface View extends IBaseView{
        void onAuthInfo(AuthInfo authInfo);
    }

    interface Presenter extends IPresenter<View>{
        void getAuthInfo();
    }
}
