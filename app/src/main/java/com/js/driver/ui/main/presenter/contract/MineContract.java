package com.js.driver.ui.main.presenter.contract;

import com.js.driver.model.bean.UserInfo;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/1.
 */
public interface MineContract {

    interface View extends IBaseView {
        void onUserInfo(UserInfo userInfo);


        void finishRefresh();
    }

    interface Presenter extends IPresenter<View> {
        void getUserInfo();
    }
}
