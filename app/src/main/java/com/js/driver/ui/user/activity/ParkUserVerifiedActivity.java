package com.js.driver.ui.user.activity;

import android.content.Context;
import android.content.Intent;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.ui.user.presenter.ParkUserVerifiedPresenter;
import com.js.driver.ui.user.presenter.contract.ParkUserVerifiedContract;
import com.xlgcx.frame.view.BaseActivity;

/**
 * Created by huyg on 2019/4/24.
 */
public class ParkUserVerifiedActivity extends BaseActivity<ParkUserVerifiedPresenter> implements ParkUserVerifiedContract.View {


    public static void action(Context context){
        context.startActivity(new Intent(context,ParkUserVerifiedActivity.class));
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initInject() {
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(App.getInstance().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_park_user_verified;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("园区成员认证");
    }
}
