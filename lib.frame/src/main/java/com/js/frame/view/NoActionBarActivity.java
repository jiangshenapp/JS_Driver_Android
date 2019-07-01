package com.xlgcx.frame.view;

import android.app.Activity;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huyg on 2018/8/22.
 */

public abstract class NoActionBarActivity extends RxAppCompatActivity {

    private Unbinder mUnBinder;
    protected Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


    public void showProgress(int messageId) {

    }

    public void closeProgress() {
    }


    protected abstract int getLayout();

    protected abstract void init();
}
