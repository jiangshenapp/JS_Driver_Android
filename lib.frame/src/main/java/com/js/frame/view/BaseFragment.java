package com.js.frame.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.js.frame.mvp.IBaseView;
import com.js.frame.mvp.IPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huyg on 2018/8/22.
 */

public abstract class BaseFragment<T extends IPresenter> extends RxFragment implements IBaseView {


    @Inject
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected Unbinder mUnBinder;
    protected CircleProgressDialog mDialog;
    private static final String TAG = "BaseFragment";


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        initInject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, mView);
        mPresenter.attachView(this);
        init();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        mUnBinder.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, getClass().getSimpleName() + "  onStart");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, getClass().getSimpleName() + "  onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, getClass().getSimpleName() + "  onStop");

    }



    @Subscribe
    public void onEvent(Object object) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showProgress() {
        if (mDialog == null) {
            mDialog = new CircleProgressDialog(mContext);
        }
        if (mDialog != null && !mDialog.isShowing() && this.isAdded()) {
            mDialog.show();
        }
    }

    @Override
    public void closeProgress() {
        if (mDialog != null && mDialog.isShowing() && this.isAdded()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void toast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void init();


}
