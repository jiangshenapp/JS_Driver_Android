package com.js.driver.ui.main.presenter.contract;

import com.js.driver.model.bean.AccountInfo;
import com.js.driver.model.bean.UserInfo;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/1.
 */
public interface MineContract {

    interface View extends IBaseView {
        void onUserInfo(UserInfo userInfo);
        void onAccountInfo(AccountInfo accountInfo);
        void finishRefresh();
    }

    interface Presenter extends IPresenter<View> {
        void getUserInfo();
        void getAccountInfo();
    }
}
