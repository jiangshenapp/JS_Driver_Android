package com.js.driver.ui.user.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerFragmentComponent;
import com.js.driver.di.module.FragmentModule;
import com.js.driver.model.event.LoginChangeEvent;
import com.js.driver.model.event.UserStatusChangeEvent;
import com.js.driver.ui.user.activity.ForgetPwdActivity;
import com.js.driver.ui.user.activity.RegisterActivity;
import com.js.driver.ui.user.presenter.PwdLoginPresenter;
import com.js.driver.ui.user.presenter.contract.PwdLoginContract;
import com.js.driver.util.RegexUtils;
import com.js.frame.view.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/21.
 */
public class PwdLoginFragment extends BaseFragment<PwdLoginPresenter> implements PwdLoginContract.View {

    @BindView(R.id.tv_register)
    TextView mRegister;
    @BindView(R.id.edit_phone)
    EditText mPhone;
    @BindView(R.id.edit_pwd)
    EditText mPwd;

    private String phone;
    private String pwd;

    public static PwdLoginFragment newInstance() {
        return new PwdLoginFragment();
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
        return R.layout.fragment_login_password;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.tv_register, R.id.tv_forget_pwd, R.id.tv_protocal, R.id.btn_login, R.id.tv_login_phonecode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                RegisterActivity.action(getActivity());
                break;
            case R.id.tv_forget_pwd:
                ForgetPwdActivity.action(getActivity());
                break;
            case R.id.tv_protocal:
                break;
            case R.id.btn_login:
                phone = mPhone.getText().toString().trim();
                pwd = mPwd.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    toast("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobile(phone)) {
                    toast("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    toast("请输入密码");
                    return;
                }
                mPresenter.login(phone, pwd);
                break;
            case R.id.tv_login_phonecode:
                EventBus.getDefault().post(new LoginChangeEvent(1));
                break;
        }
    }

    @Override
    public void onLogin(String token) {
        toast("登录成功");
        App.getInstance().putToken(token);
        EventBus.getDefault().post(new UserStatusChangeEvent(UserStatusChangeEvent.LOGIN_SUCCESS));
        getActivity().finish();
    }
}
