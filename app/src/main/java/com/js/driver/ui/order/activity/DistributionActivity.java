package com.js.driver.ui.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.global.Const;
import com.js.driver.model.bean.CarBean;
import com.js.driver.ui.center.activity.CarsActivity;
import com.js.driver.ui.order.presenter.DistributionPresenter;
import com.js.driver.ui.order.presenter.contract.DistributionContract;
import com.js.frame.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019-06-19.
 * 分配司机
 */
public class DistributionActivity extends BaseActivity<DistributionPresenter> implements DistributionContract.View {


    @BindView(R.id.driver_name)
    TextView mName;
    @BindView(R.id.car)
    TextView mCar;
    @BindView(R.id.confirm)
    TextView confirm;

    public static void action(Activity activity){
        activity.startActivityForResult(new Intent(activity,DistributionActivity.class),Const.CODE_REQ);
    }

    private CarBean mCarBean;

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mName.setText(App.getInstance().nickName);
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
        return R.layout.activity_distribution;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("分配司机");
    }


    @OnClick({R.id.driver_name_layout, R.id.confirm,R.id.car_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.driver_name_layout:

                break;
            case R.id.car_layout:
                CarsActivity.action(DistributionActivity.this, false);
                break;
            case R.id.confirm:
                if (mCarBean == null) {
                    toast("请选择车辆");
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("carId", mCarBean.getId());
                setResult(Const.CODE_REQ, intent);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.CODE_REQ) {
            if (data != null) {
                mCarBean = data.getParcelableExtra("car");
                if (mCarBean != null) {
                    mCar.setText(mCarBean.getCphm());
                }
            }
        }
    }
}
