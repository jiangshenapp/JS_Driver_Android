package com.js.frame.view;

import android.app.Activity;
import android.os.Bundle;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huyg on 2018/8/22.
 */

public abstract class SimpleActivity extends ToolbarActivity {

    private Unbinder mUnBinder;
    protected Activity mContext;
    protected CircleProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void init();



    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Subscribe
    public void onEvent(Object event) {

    }

    public void showProgress() {
        if (mDialog == null) {
            mDialog = new CircleProgressDialog(this);
        }
        if (mDialog != null && !mDialog.isShowing()&&!this.isFinishing()) {
            mDialog.show();
        }
    }

    public void closeProgress() {
        if (mDialog != null && mDialog.isShowing()&&!this.isFinishing()) {
            mDialog.dismiss();
        }
    }

}
