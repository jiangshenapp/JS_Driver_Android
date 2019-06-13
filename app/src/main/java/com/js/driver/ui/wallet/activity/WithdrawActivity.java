package com.js.driver.ui.wallet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.ui.order.activity.OrderDetailActivity;
import com.js.driver.ui.wallet.presenter.WithdrawPresenter;
import com.js.driver.ui.wallet.presenter.contract.WithdrawContract;
import com.xlgcx.frame.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 */
public class WithdrawActivity extends BaseActivity<WithdrawPresenter> implements WithdrawContract.View {

    @BindView(R.id.withdraw_money)
    EditText mMoney;
    @BindView(R.id.withdraw_money_max)
    TextView mMoneyMax;
    @BindView(R.id.bank_number)
    EditText mBankNumber;
    @BindView(R.id.bank_opening)
    EditText mBankOpening;
    @BindView(R.id.bank_sub)
    EditText mBankSub;
    @BindView(R.id.item_bank_info_layout)
    LinearLayout mBankInfoLayout;
    @BindView(R.id.item_alipay_img)
    ImageView itemAlipayImg;
    @BindView(R.id.item_alipay_layout)
    LinearLayout itemAlipayLayout;
    @BindView(R.id.alipay_no)
    EditText alipayNo;
    @BindView(R.id.alipay_name)
    EditText alipayName;
    @BindView(R.id.item_balance_img)
    ImageView itemBalanceImg;
    @BindView(R.id.item_bank_layout)
    LinearLayout itemBankLayout;

    private int mWithdrawType; //1、运力端保证金，2、账户余额
    private double moneyMax; //最大提现金额
    private int mWithdrawChannel; //1、支付宝 2、银行卡

    public static void action(Context context, int withdrawType, double money) {
        Intent intent = new Intent(context, WithdrawActivity.class);
        intent.putExtra("withdrawType", withdrawType);
        intent.putExtra("money", money);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        initIntent();
        mWithdrawChannel = 1;
        mMoney.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        mMoney.setEnabled(false);
        mMoney.setText(String.valueOf(moneyMax));
        mMoneyMax.setText("当前最大提现金额："+ moneyMax +"元");
    }

    private void initIntent() {
        mWithdrawType = getIntent().getIntExtra("withdrawType", 0);
        moneyMax = getIntent().getIntExtra("money", 0);
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
        return R.layout.activity_wallet_withdraw;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("提现");
    }

    @OnClick({R.id.item_alipay_layout, R.id.item_bank_layout, R.id.wallet_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_alipay_layout://支付宝
                mWithdrawChannel = 1;
                itemAlipayImg.setImageResource(R.mipmap.ic_checkbox_selected);
                itemBalanceImg.setImageResource(R.mipmap.ic_checkbox_default);
                break;
            case R.id.item_bank_layout://银行卡
                itemAlipayImg.setImageResource(R.mipmap.ic_checkbox_default);
                itemBalanceImg.setImageResource(R.mipmap.ic_checkbox_selected);
                mWithdrawChannel = 2;
                break;
            case R.id.wallet_withdraw://申请提现
                if (mMoney.getText().toString().isEmpty()) {
                    toast("请输入提现金额");
                    return;
                }
                double money = Double.parseDouble(mMoney.getText().toString());
                if (money>moneyMax) {
                    toast("超过最大提现金额，请重新输入");
                    return;
                }
                if (mWithdrawChannel == 1) {
                    if (alipayNo.getText().toString().isEmpty()) {
                        toast("请输入支付宝账户");
                        return;
                    }
                    if (alipayName.getText().toString().isEmpty()) {
                        toast("请输入支付宝账户姓名");
                        return;
                    }
                }
                if (mWithdrawChannel == 2) {
                    if (mBankNumber.getText().toString().isEmpty()) {
                        toast("请输入卡号");
                        return;
                    }
                    if (mBankOpening.getText().toString().isEmpty()) {
                        toast("请输入开户行");
                        return;
                    }
                    if (mBankSub.getText().toString().isEmpty()) {
                        toast("请输入支行");
                        return;
                    }
                }
                mPresenter.balanceWithdraw(mWithdrawType,mWithdrawChannel,
                        mBankNumber.getText().toString(),mBankOpening.getText().toString(),mBankSub.getText().toString(),
                        alipayNo.getText().toString(),alipayName.getText().toString());
                break;
        }
    }

    @Override
    public void onBalanceWithdraw() {
        toast("申请提现成功");
        backAction();
    }
}
