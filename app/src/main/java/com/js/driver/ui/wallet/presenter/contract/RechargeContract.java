package com.js.driver.ui.wallet.presenter.contract;

import com.js.driver.model.bean.PayInfo;
import com.js.driver.model.bean.PayRouter;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

import java.util.List;

import retrofit2.http.Field;

/**
 * Created by huyg on 2019/4/24.
 */
public interface RechargeContract {

    interface View extends IBaseView {
        void onPayOrder(PayInfo payInfo);

        void onPayRouter(List<PayRouter> payRouters);
    }

    interface Presenter extends IPresenter<View> {
        void payOrder(int channelType, double money, int routeId);

        void getPayRouter();
    }
}
