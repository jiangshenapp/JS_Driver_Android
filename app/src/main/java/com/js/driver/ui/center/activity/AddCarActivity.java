package com.js.driver.ui.center.activity;

import android.content.Context;
import android.content.Intent;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.model.bean.CarBean;
import com.js.driver.ui.center.presenter.AddCarPresenter;
import com.js.driver.ui.center.presenter.contract.AddCarContract;
import com.xlgcx.frame.view.BaseActivity;

/**
 * Created by huyg on 2019/4/29.
 */
public class AddCarActivity extends BaseActivity<AddCarPresenter> implements AddCarContract.View {

    public static void action(Context context){
        context.startActivity(new Intent(context,AddCarActivity.class));
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {

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
        return R.layout.activity_add_car;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("添加车辆");
    }

    @Override
    public void onCarDetail(CarBean carBean) {

    }

    @Override
    public void onBindingCar() {

    }

    @Override
    public void onUnbindingCar() {

    }
}
