package com.js.driver.ui.center.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.model.bean.RouteBean;
import com.js.driver.ui.center.presenter.RoutesDetailPresenter;
import com.js.driver.ui.center.presenter.contract.RoutesDetailContract;
import com.xlgcx.frame.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/12
 * desc   : 路线详情
 * version: 3.0.0
 */
public class RoutesDetailActivity extends BaseActivity<RoutesDetailPresenter> implements RoutesDetailContract.View {

    public static long mId;
    @BindView(R.id.tv_address_start)
    TextView tvAddressStart;
    @BindView(R.id.tv_address_end)
    TextView tvAddressEnd;
    @BindView(R.id.tv_car_length)
    TextView tvCarLength;
    @BindView(R.id.tv_car_model)
    TextView tvCarModel;
    @BindView(R.id.tv_car_desc)
    TextView tvCarDesc;
    @BindView(R.id.tv_on_off)
    TextView tvOnOff;
    @BindView(R.id.tv_apply)
    TextView tvApply;

    private RouteBean mRouteBean;

    public static void action(Context context, long id) {
        Intent intent = new Intent(context, RoutesDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        initIntent();
        mPresenter.getRouteDetail(mId);
    }

    private void initIntent() {
        mId = getIntent().getLongExtra("id", 0);
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
        return R.layout.activity_mine_route_detail;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("路线详情");
    }

    @Override
    public void onRouteDetail(RouteBean routeBean) {

        mRouteBean = routeBean;

        tvAddressStart.setText(routeBean.getStartAddressCodeName());
        tvAddressEnd.setText(routeBean.getArriveAddressCodeName());
        tvCarLength.setText(routeBean.getCarLengthName());
        tvCarModel.setText(routeBean.getCarModelName());
        tvCarDesc.setText(routeBean.getRemark());

        if (routeBean.getClassic() == 0) { //是否精品路线，1是，0否，2审核中
            tvApply.setVisibility(View.VISIBLE);
        } else {
            tvApply.setVisibility(View.GONE);
        }

        if (routeBean.getState() == 0) { //未启用
            tvOnOff.setText("启用");
            tvOnOff.setBackground(getResources().getDrawable(R.drawable.shape_btn_4a90e2));
        } else {
            tvOnOff.setText("停用");
            tvOnOff.setBackground(getResources().getDrawable(R.drawable.shape_btn_d0021b));
        }
    }

    @Override
    public void onApplyClassicLine() {
        toast("申请品牌路线成功");
        finish();
    }

    @Override
    public void onEnableLine() {
        if (tvOnOff.getText().equals("启用")) {
            toast("启用路线成功");
            tvOnOff.setText("停用");
            tvOnOff.setBackground(getResources().getDrawable(R.drawable.shape_btn_d0021b));
        } else {
            toast("停用路线成功");
            tvOnOff.setText("启用");
            tvOnOff.setBackground(getResources().getDrawable(R.drawable.shape_btn_4a90e2));
        }
    }

    @OnClick({R.id.tv_on_off, R.id.tv_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_on_off:
                if (tvOnOff.getText().equals("启用")) {
                    mPresenter.enableLine(mRouteBean.getId(),1);
                } else {
                    mPresenter.enableLine(mRouteBean.getId(),0);
                }
                break;
            case R.id.tv_apply:
                mPresenter.applyClassicLine(mRouteBean.getId());
                break;
        }
    }
}
