package com.js.driver.ui.wallet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.model.bean.AccountInfo;
import com.js.driver.ui.center.activity.AddRouteActivity;
import com.js.driver.ui.wallet.presenter.BailPresenter;
import com.js.driver.ui.wallet.presenter.contract.BailContract;
import com.xlgcx.frame.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 * 我的保证金
 */
public class BailActivity extends BaseActivity<BailPresenter> implements BailContract.View {

    @BindView(R.id.bail_money)
    TextView mMoney;

    private AccountInfo mAccountInfo;

    public static void action(Context context, AccountInfo accountInfo) {
        Intent intent = new Intent(context, BailActivity.class);
        intent.putExtra("accountInfo", accountInfo);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        initIntent();
        mMoney.setText(String.valueOf(mAccountInfo.getDriverDeposit()));
    }

    private void initIntent() {
        mAccountInfo = getIntent().getParcelableExtra("accountInfo");
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
        return R.layout.activity_wallet_bail;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("我的保证金");
    }


    @OnClick({R.id.bail_withdraw, R.id.bail_recharge, R.id.bail_detail_layout, R.id.statement_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bail_withdraw://提现
                if (mAccountInfo.getDriverDeposit()<=0) {
                    toast("提现金额必须大于0元");
                    return;
                }
                WithdrawActivity.action(mContext, 1, mAccountInfo.getDriverDeposit());
                break;
            case R.id.bail_recharge://充值
                RechargeBailActivity.action(mContext, mAccountInfo);
                break;
            case R.id.bail_detail_layout://明细
                BillActivity.action(mContext,2);
                break;
            case R.id.statement_layout://违约说明
                toast("违约说明");
                break;
        }
    }
}
