package com.js.driver.ui.wallet.presenter.contract;

import com.js.driver.model.bean.PayInfo;
import com.js.driver.model.bean.PayRouter;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

import java.util.List;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/08
 * desc   :
 * version: 3.0.0
 */
public interface RechargeBailContract {

    interface View extends IBaseView {
        void onPayOrder(PayInfo payInfo);

        void onPayRouter(List<PayRouter> payRouters);

        void onPayAccount(Boolean isOk);
    }

    interface Presenter extends IPresenter<View> {
        void payOrder(int tradeType, int channelType, double money, int routeId);

        void getPayRouter();

        void payAccount(double deposit);
    }
}
