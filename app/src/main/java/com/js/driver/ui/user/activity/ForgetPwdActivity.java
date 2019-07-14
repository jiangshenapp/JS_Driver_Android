package com.js.driver.ui.user.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.ui.user.presenter.ForgetPwdPresenter;
import com.js.driver.ui.user.presenter.SmsCodePresenter;
import com.js.driver.ui.user.presenter.contract.ForgetPwdContract;
import com.js.driver.ui.user.presenter.contract.SmsCodeContract;
import com.js.driver.util.AppUtils;
import com.js.frame.view.BaseActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/02
 * desc   : 忘记密码第一步
 * version: 3.0.0
 */
public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresenter> implements ForgetPwdContract.View, SmsCodeContract.View {

    @BindView(R.id.edit_phone)
    EditText mPhone;
    @BindView(R.id.edit_code)
    EditText mCode;
    @BindView(R.id.tv_get_code)
    TextView mGetCode;
    @BindView(R.id.btn_next_step)
    TextView mNextStep;

    private String phone;
    private String code;
    private Disposable mDisposable;

    @Inject
    SmsCodePresenter mCodePresenter;

    public static void action(Context context) {
        context.startActivity(new Intent(context, ForgetPwdActivity.class));
    }

    @Override
    protected void init() {
        mCodePresenter.attachView(this);
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
        return R.layout.activity_pwd_forget;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("");
    }

    @OnClick({R.id.tv_get_code, R.id.btn_next_step})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                phone = mPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    toast("请输入手机号");
                    return;
                }
                if (!AppUtils.isMobile(phone)){
                    toast("请输入正确手机号");
                    return;
                }
                mCodePresenter.sendSmsCode(phone);
                break;
            case R.id.btn_next_step:
                phone = mPhone.getText().toString().trim();
                code = mCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    toast("请输入手机号");
                    return;
                }
                if (!AppUtils.isMobile(phone)){
                    toast("请输入正确手机号");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    toast("请输入验证码");
                    return;
                }
                mPresenter.forgetPwd(phone, code);
                break;
        }
    }

    @Override
    public void onForgetPwd() {
        ResetPwdActivity.action(this, phone);
    }

    @Override
    public void onSmsCode() {
        mGetCode.setClickable(false);
        mDisposable = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mGetCode.setText(String.format("%d秒", 60 - aLong));
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mGetCode.setClickable(true);
                        mGetCode.setText("重新获取");
                    }
                }).subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
