package com.js.driver.ui.wallet.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.model.bean.AccountInfo;
import com.js.driver.model.event.AccountChangeEvent;
import com.js.driver.model.event.LoginChangeEvent;
import com.js.driver.ui.wallet.presenter.WalletPresenter;
import com.js.driver.ui.wallet.presenter.contract.WalletContract;
import com.xlgcx.frame.view.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 */
public class WalletActivity extends BaseActivity<WalletPresenter> implements WalletContract.View {

    @BindView(R.id.wallet_money)
    TextView mMoney;
    @BindView(R.id.wallet_withdraw)
    TextView walletWithdraw;
    @BindView(R.id.wallet_bail)
    TextView mBail;

    AccountInfo mAccountInfo;

    @OnClick({R.id.wallet_withdraw, R.id.wallet_recharge, R.id.wallet_bail_layout, R.id.bill_detail_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wallet_withdraw://提现
                WithdrawActivity.action(mContext, 2, mAccountInfo.getBalance());
                break;
            case R.id.wallet_recharge://充值
                RechargeActivity.action(mContext);
                break;
            case R.id.wallet_bail_layout://保证金
                BailActivity.action(mContext);
                break;
            case R.id.bill_detail_layout://账单明细
                BillActivity.action(mContext, 1);
                break;
        }
    }

    @Subscribe
    public void onEvent(AccountChangeEvent event) {
        switch (event.index) {
            case AccountChangeEvent.WALLET_CHANGE:
                mPresenter.getAccountInfo();
                break;
        }
    }

    public static void action(Context context) {
        context.startActivity(new Intent(context, WalletActivity.class));
    }

    @Override
    protected void init() {
        mPresenter.getAccountInfo();
        initView();
    }

    private void initView() {

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
        return R.layout.activity_wallet;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("我的钱包");
    }

    @Override
    public void onAccountInfo(AccountInfo accountInfo) {
        mAccountInfo = accountInfo;
        mMoney.setText(String.valueOf(accountInfo.getBalance()));
        mBail.setText(String.valueOf(accountInfo.getDriverDeposit()));
    }
}
