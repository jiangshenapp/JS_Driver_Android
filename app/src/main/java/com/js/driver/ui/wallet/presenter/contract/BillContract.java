package com.js.driver.ui.wallet.presenter.contract;

import com.js.driver.model.bean.BillBean;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.bean.PayRouter;
import com.js.driver.model.response.ListResponse;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

import java.util.List;

/**
 * Created by huyg on 2019/4/24.
 */
public interface BillContract {

    interface View extends IBaseView {
        void onBillList(List<BillBean> billBeans);

        void finishRefreshAndLoadMore();
    }

    interface Presenter extends IPresenter<View> {
        void getBillList(int status);
    }
}
