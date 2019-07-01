package com.js.driver.ui.main.presenter;

import com.js.driver.ui.main.presenter.contract.InformationContract;
import com.js.frame.mvp.RxPresenter;

import javax.inject.Inject;

/**
 * Created by huyg on 2019/4/1.
 */
public class InformationPresenter extends RxPresenter<InformationContract.View> implements InformationContract.Presenter {

    @Inject
    public InformationPresenter(){

    }
}
