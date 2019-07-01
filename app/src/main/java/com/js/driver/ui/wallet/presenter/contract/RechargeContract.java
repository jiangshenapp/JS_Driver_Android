package com.js.driver.ui.wallet.presenter.contract;

import com.js.driver.model.bean.PayInfo;
import com.js.driver.model.bean.PayRouter;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

import java.util.List;

/**
 * Created by huyg on 2019/4/24.
 */
public interface RechargeContract {

    interface View extends IBaseView {
        void onPayOrder(PayInfo payInfo);

        void onPayRouter(List<PayRouter> payRouters);
    }

    interface Presenter extends IPresenter<View> {
        void payOrder(int tradeType, int channelType, double money, int routeId);

        void getPayRouter();
    }
}
