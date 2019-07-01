package com.js.driver.ui.user.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.js.driver.R;
import com.js.driver.model.event.LoginChangeEvent;
import com.js.driver.ui.main.activity.MainActivity;
import com.js.driver.ui.order.activity.OrderDetailActivity;
import com.js.driver.ui.user.fragment.CodeLoginFragment;
import com.js.driver.ui.user.fragment.PwdLoginFragment;
import com.xlgcx.frame.view.SimpleActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

/**
 * Created by huyg on 2019/4/21.
 */
@Route(path = "/user/login")
public class LoginActivity extends SimpleActivity {

    @BindView(R.id.login_frame)
    FrameLayout mFrame;

    private CodeLoginFragment mCodeLoginFragment;
    private PwdLoginFragment mPwdLoginFragment;
    private List<Fragment> mFragments;
    private Fragment mCurrentFragment;

    public static void action(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void backAction() {
        MainActivity.action(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        initView();
    }


    private void initView() {
        initFragment();
        showFragment();
    }

    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!mPwdLoginFragment.isAdded()) {
            transaction.add(R.id.login_frame, mPwdLoginFragment);
        }
        transaction.show(mPwdLoginFragment).commit();
        mCurrentFragment = mPwdLoginFragment;
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mCodeLoginFragment = CodeLoginFragment.newInstance();
        mPwdLoginFragment = PwdLoginFragment.newInstance();
        mFragments.add(mPwdLoginFragment);
        mFragments.add(mCodeLoginFragment);
    }

    @Override
    public void setActionBar() {
        mTitle.setText("");
        mToolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_navigationbar_close_black));
    }

    @Subscribe
    public void onEvent(LoginChangeEvent event) {
        switchFragment(event.index);
    }

    private void switchFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mCurrentFragment);
        if (!mFragments.get(index).isAdded()) {
            transaction.add(R.id.login_frame, mFragments.get(index));
        }
        transaction.show(mFragments.get(index)).commitAllowingStateLoss();
        mCurrentFragment = mFragments.get(index);
    }

}
