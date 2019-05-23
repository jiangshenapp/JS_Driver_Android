package com.js.driver.ui.user.presenter.contract;

import com.js.driver.model.bean.AuthInfo;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/24.
 */
public interface ParkUserVerifiedContract {

    interface View extends IBaseView{
        void onParkUserVerifiedInfo(AuthInfo authInfo);
        void onSubmitParkUserVerified();
    }

    interface Presenter extends IPresenter<View>{
        void getParkUserVerifiedInfo();
        void submitParkUserVerified(String companyName, String companyType, String registrationNumber, String address, String detailAddress, String businessLicenceImage);
    }
}
