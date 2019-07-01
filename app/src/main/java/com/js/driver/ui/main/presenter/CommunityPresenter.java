package com.js.driver.ui.main.presenter;

import com.js.driver.ui.main.presenter.contract.CommunityContract;
import com.js.frame.mvp.RxPresenter;

import javax.inject.Inject;

/**
 * Created by huyg on 2019/4/1.
 */
public class CommunityPresenter extends RxPresenter<CommunityContract.View> implements CommunityContract.Presenter {

    @Inject
    public CommunityPresenter(){

    }
}
