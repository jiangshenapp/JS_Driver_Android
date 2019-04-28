package com.js.driver.presenter.contract;

import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

import java.io.File;

/**
 * Created by huyg on 2019/4/26.
 */
public interface FileContract {

    interface View extends IBaseView {
        void onUploadFile(String data);
    }

    interface Presenter extends IPresenter<View>{
        void uploadFile(File file);
    }
}
