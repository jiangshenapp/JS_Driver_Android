package com.js.driver.ui.center.activity;

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
import com.js.driver.ui.center.presenter.FeedBackPresenter;
import com.js.driver.ui.center.presenter.contract.FeedBackContract;
import com.xlgcx.frame.view.BaseActivity;
import com.xlgcx.frame.view.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/22
 * desc   :
 * version: 3.0.0
 */
public class FeedBackActivity extends BaseActivity<FeedBackPresenter> implements FeedBackContract.View {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;

    public static void action(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
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
        return R.layout.activity_feedback;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("意见反馈");
    }

    @OnClick(R.id.btn_submit)
    public void onClick() {
        String suggest = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(suggest)) {
            toast("意见反馈内容不能为空");
            return;
        }
        toast("意见反馈提交成功");
    }
}
