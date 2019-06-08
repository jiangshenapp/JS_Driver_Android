package com.js.driver.ui.wallet.presenter.contract;

import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/24.
 */
public interface WithdrawContract {

    interface View extends IBaseView {
        void onBalanceWithdraw();
    }

    interface Presenter extends IPresenter<View> {
        void balanceWithdraw(int withdrawType, int withdrawChannel,
                             String bankCard, String khh, String zh,
                             String zfbzh, String zfbzhxm);
    }
}
