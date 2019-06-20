package com.js.driver.ui;

import android.content.Intent;
import android.view.View;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.presenter.SplashPresenter;
import com.js.driver.presenter.contract.SplashContract;
import com.js.driver.service.LocationService;
import com.js.driver.ui.main.activity.MainActivity;
import com.xlgcx.frame.view.BaseActivity;

import java.util.List;

/**
 * Created by huyg on 2019-05-23.
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {
    @Override
    protected void init() {
        initPermission();
        initLocation();
    }

    private void initLocation() {
        Intent intent = new Intent(mContext, LocationService.class);
        startService(intent);
    }

    private void initPermission() {
        XXPermissions.with(this)
                //.constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .permission(Permission.ACCESS_COARSE_LOCATION,
                        Permission.ACCESS_FINE_LOCATION,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            MainActivity.action(mContext);
                        }
                        finish();
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        finish();
                    }
                });
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
        return R.layout.activity_splash;
    }

    @Override
    public void setActionBar() {
        mToolbar.setVisibility(View.GONE);
    }
}
