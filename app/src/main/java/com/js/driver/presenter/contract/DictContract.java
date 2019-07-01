package com.js.driver.presenter.contract;

import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;
import com.js.driver.model.bean.DictBean;

import java.util.List;

/**
 * Created by huyg on 2019-06-09.
 */
public interface DictContract {


    interface View extends IBaseView {

        void onDictByType(String type, List<DictBean> dictBeans);

    }

    interface Presenter extends IPresenter<View> {

        void getDictByType(String type);

    }
}
