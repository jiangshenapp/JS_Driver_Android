package com.js.driver.ui.wallet.presenter.contract;

import com.js.driver.model.bean.AccountInfo;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/24.
 */
public interface BailContract {

    interface View extends IBaseView {
        void onAccountInfo(AccountInfo accountInfo);
    }

    interface Presenter extends IPresenter<View> {
        void getAccountInfo();
    }
}
