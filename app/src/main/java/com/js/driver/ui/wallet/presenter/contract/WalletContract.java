package com.js.driver.ui.wallet.presenter.contract;

import com.js.driver.model.bean.AccountInfo;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/24.
 */
public interface WalletContract {

    interface View extends IBaseView {
        void onAccountInfo(AccountInfo accountInfo);
        void finishRefresh();
    }

    interface Presenter extends IPresenter<View> {
        void getAccountInfo();
    }

}
