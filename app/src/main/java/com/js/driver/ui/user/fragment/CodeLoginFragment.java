package com.js.driver.ui.user.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerFragmentComponent;
import com.js.driver.di.module.FragmentModule;
import com.js.driver.manager.SpManager;
import com.js.driver.model.event.LoginChangeEvent;
import com.js.driver.model.event.UserStatusChangeEvent;
import com.js.driver.ui.main.activity.MainActivity;
import com.js.driver.ui.user.activity.RegisterActivity;
import com.js.driver.ui.user.presenter.CodeLoginPresenter;
import com.js.driver.ui.user.presenter.SmsCodePresenter;
import com.js.driver.ui.user.presenter.contract.CodeLoginContract;
import com.js.driver.ui.user.presenter.contract.SmsCodeContract;
import com.xlgcx.frame.view.BaseFragment;
import com.xlgcx.http.HttpApp;

import org.greenrobot.eventbus.EventBus;

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
 * Created by huyg on 2019/4/21.
 */
public class CodeLoginFragment extends BaseFragment<CodeLoginPresenter> implements CodeLoginContract.View, SmsCodeContract.View {

    @BindView(R.id.edit_phone)
    EditText mPhone;
    @BindView(R.id.edit_code)
    EditText mCode;
    @BindView(R.id.tv_protocal)
    TextView tvProtocal;
    @BindView(R.id.tv_get_code)
    TextView mGetCode;

    private String phone;
    private String code;
    private Disposable mDisposable;

    @Inject
    SmsCodePresenter mCodePresenter;

    public static CodeLoginFragment newInstance() {
        return new CodeLoginFragment();
    }

    @Override
    protected void initInject() {
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .appComponent(App.getInstance().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_phonecode;
    }

    @Override
    protected void init() {
        mCodePresenter.attachView(this);
    }

    @OnClick({R.id.tv_register, R.id.tv_get_code, R.id.tv_protocal, R.id.btn_login, R.id.tv_login_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                RegisterActivity.action(getActivity());
                break;
            case R.id.tv_get_code:
                phone = mPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    toast("请输入手机号");
                    return;
                }
                mCodePresenter.sendSmsCode(phone);
                break;
            case R.id.tv_protocal:
                break;
            case R.id.btn_login:
                phone = mPhone.getText().toString().trim();
                code = mCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    toast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    toast("请输入验证码");
                    return;
                }
                mPresenter.login(phone, code);
                break;
            case R.id.tv_login_password:
                EventBus.getDefault().post(new LoginChangeEvent(0));
                break;
        }
    }

    @Override
    public void onSmsCode() {
        mGetCode.setClickable(false);
        mDisposable = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isVisible()) {
                            mGetCode.setText(String.format("%d秒", 60 - aLong));
                        }
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isVisible()) {
                            mGetCode.setClickable(true);
                            mGetCode.setText("重新获取");
                        }
                    }
                }).subscribe();
    }

    @Override
    public void onLogin(String token) {
        App.getInstance().putToken(token);
        EventBus.getDefault().post(new UserStatusChangeEvent(UserStatusChangeEvent.LOGIN_SUCCESS));
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
