package com.js.driver.di.componet;

import android.app.Activity;

import com.js.driver.di.ActivityScope;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.ui.center.activity.AddCarActivity;
import com.js.driver.ui.center.activity.CarsActivity;
import com.js.driver.ui.center.activity.DriversActivity;
import com.js.driver.ui.center.activity.FeedBackActivity;
import com.js.driver.ui.main.activity.MainActivity;
import com.js.driver.ui.order.activity.OrderDetailActivity;
import com.js.driver.ui.user.activity.DriverVerifiedActivity;
import com.js.driver.ui.user.activity.ForgetPwdActivity;
import com.js.driver.ui.user.activity.ParkUserVerifiedActivity;
import com.js.driver.ui.user.activity.RegisterActivity;
import com.js.driver.ui.user.activity.ResetPwdActivity;
import com.js.driver.ui.user.activity.UserCenterActivity;
import com.js.driver.ui.wallet.activity.BailActivity;
import com.js.driver.ui.wallet.activity.RechargeActivity;
import com.js.driver.ui.wallet.activity.WalletActivity;
import com.js.driver.ui.wallet.activity.WithdrawActivity;

import dagger.Component;

/**
 * Created by huyg on 2018/8/22.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


    void inject(MainActivity mainActivity);

    void inject(UserCenterActivity userCenterActivity);

    void inject(WalletActivity walletActivity);

    void inject(BailActivity bailActivity);

    void inject(RechargeActivity rechargeActivity);

    void inject(WithdrawActivity withdrawActivity);

    void inject(RegisterActivity registerActivity);

    void inject(DriverVerifiedActivity driverVerifiedActivity);

    void inject(ParkUserVerifiedActivity parkUserVerifiedActivity);

    void inject(OrderDetailActivity orderDetailActivity);

    void inject(DriversActivity driversActivity);

    void inject(CarsActivity carsActivity);

    void inject(AddCarActivity addCarActivity);

    void inject(ForgetPwdActivity addCarActivity);

    void inject(ResetPwdActivity addCarActivity);

    void inject(FeedBackActivity feedBackActivity);
}

