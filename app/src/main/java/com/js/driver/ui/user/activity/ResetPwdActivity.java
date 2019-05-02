package com.js.driver.ui.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.ui.user.presenter.ResetPwdPresenter;
import com.js.driver.ui.user.presenter.SmsCodePresenter;
import com.js.driver.ui.user.presenter.contract.ResetPwdContract;
import com.xlgcx.frame.view.BaseActivity;

import java.security.PublicKey;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/02
 * desc   : 忘记密码第二步
 * version: 3.0.0
 */
public class ResetPwdActivity extends BaseActivity<ResetPwdPresenter> implements ResetPwdContract.View {

    @BindView(R.id.edit_new_pwd)
    EditText mNewPwd;
    @BindView(R.id.edit_new_pwd_again)
    EditText mNewPwdAgain;
    @BindView(R.id.btn_reset)
    TextView mReset;

    public static String mPhone;
    private String newPwd;
    private String newPwdAgain;

    public static void action(Context context, String phone) {
        mPhone = phone;
        context.startActivity(new Intent(context, ResetPwdActivity.class));
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
        return R.layout.activity_pwd_reset;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("");
    }

    @OnClick(R.id.btn_reset)
    public void onClick() {
        newPwd = mNewPwd.getText().toString().trim();
        newPwdAgain = mNewPwdAgain.getText().toString().trim();
        if (TextUtils.isEmpty(newPwd)) {
            toast("请输入新密码");
            return;
        }
        if (newPwd.length()<6 || newPwd.length()>16) {
            toast("请输入6-16位新密码（字母、数字）");
            return;
        }
        if (TextUtils.isEmpty(newPwdAgain)) {
            toast("请再次输入新密码");
            return;
        }
        if (!newPwd.equals(newPwdAgain)) {
            toast("前后密码不一致，请检查");
            return;
        }
        mPresenter.resetPwd(mPhone, newPwd);
    }

    @Override
    public void onResetPwd() {
        LoginActivity.action(this,false);
    }
}
