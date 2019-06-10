package com.js.driver.ui.center.presenter.contract;

import com.js.driver.model.bean.CarBean;
import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/29.
 */
public interface AddCarContract {

    interface View extends IBaseView{
        void onCarDetail(CarBean carBean);
        void onBindingCar();
        void onUnbindingCar();
    }

    interface Presenter extends IPresenter<View>{
        void getCarDetail(long id);
        void bindingCar(String image1, String carModelId, String image2, String capacityVolume, String state, String carLengthId, String cphm, String capacityTonnage);
        void unbindingCar(long id);
    }
}
