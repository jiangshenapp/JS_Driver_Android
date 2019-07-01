package com.js.driver.ui.center.presenter.contract;

import com.js.driver.model.bean.CarBean;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

/**
 * Created by huyg on 2019/4/29.
 */
public interface AddCarContract {

    interface View extends IBaseView{
        void onCarDetail(CarBean carBean);
        void onBindingCar();
        void onUnbindingCar();
        void onReBindingCar();
    }

    interface Presenter extends IPresenter<View>{
        void getCarDetail(long id);
        void bindingCar(String image1, String carModelId, String image2, String capacityVolume, String state,
                        String carLengthId, String cphm, String capacityTonnage, String tradingNo, String transportNo);
        void unbindingCar(long id);
        void reBindingCar(long id, String image1, String carModelId, String image2, String capacityVolume, String state,
                          String carLengthId, String cphm, String capacityTonnage, String tradingNo, String transportNo);
    }
}
