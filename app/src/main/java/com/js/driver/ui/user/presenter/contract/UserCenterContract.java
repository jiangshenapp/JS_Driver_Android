package com.js.driver.ui.user.presenter.contract;

import com.xlgcx.frame.mvp.IBaseView;
import com.xlgcx.frame.mvp.IPresenter;

import retrofit2.http.Field;

/**
 * Created by huyg on 2019/4/24.
 */
public interface UserCenterContract {

    interface View extends IBaseView{
        void onChangeAvatar();
        void onChangeNickname();
    }

    interface Presenter extends IPresenter<View>{
        void changeAvatar(String avatar);
        void changeNickname(String nickname);
    }
}
