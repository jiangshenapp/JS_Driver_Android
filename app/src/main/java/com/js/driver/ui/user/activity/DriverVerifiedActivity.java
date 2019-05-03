package com.js.driver.ui.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.global.Const;
import com.js.driver.manager.CommonGlideImageLoader;
import com.js.driver.model.request.DriverAuth;
import com.js.driver.presenter.FilePresenter;
import com.js.driver.presenter.contract.FileContract;
import com.js.driver.ui.center.adapter.DriversAdapter;
import com.js.driver.ui.user.presenter.DriverVerifiedPresenter;
import com.js.driver.ui.user.presenter.contract.DriverVerifiedContract;
import com.xlgcx.frame.view.BaseActivity;

import java.io.File;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 */
public class DriverVerifiedActivity extends BaseActivity<DriverVerifiedPresenter> implements DriverVerifiedContract.View,
        FileContract.View, TakePhoto.TakeResultListener, InvokeListener {


    @BindView(R.id.auth_card)
    ImageView mAuthCard;
    @BindView(R.id.auth_body)
    ImageView mAuthBody;
    @BindView(R.id.auth_driver_card)
    ImageView mAuthDriverCard;
//    @BindView(R.id.auth_name)
//    EditText mAuthName;
//    @BindView(R.id.auth_card_number)
//    EditText mAuthCardNumber;
//    @BindView(R.id.auth_address)
//    EditText mAuthAddress;
//    @BindView(R.id.auth_driver_type)
//    EditText mAuthDriverType;
    @BindView(R.id.auth_submit)
    TextView mAuthSubmit;


    @Inject
    FilePresenter mFilePresenter;

    private int choseCode;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private DriverAuth mDriverAuth;


    public static void action(Context context) {
        context.startActivity(new Intent(context, DriverVerifiedActivity.class));
    }

    @Override
    protected void init() {
        mFilePresenter.attachView(this);
        mDriverAuth = new DriverAuth();
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
        return R.layout.activity_driver_verified;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("司机身份认证");
    }


    @OnClick({R.id.auth_card, R.id.auth_body, R.id.auth_driver_card, R.id.auth_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.auth_card:
                getPhoto(Const.AUTH_CARD);
                break;
            case R.id.auth_body:
                getPhoto(Const.AUTH_BODY);
                break;
            case R.id.auth_driver_card:
                getPhoto(Const.AUTH_DRIVER_CARD);
                break;
            case R.id.auth_submit:

                break;
        }
    }

    @Override
    public void onUploadFile(String data) {
        Log.d(getClass().getSimpleName(), data);
        switch (choseCode) {
            case Const.AUTH_CARD:
                mDriverAuth.setIdImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.xlgcx.http.global.Const.IMG_URL + data, mAuthCard);
                break;
            case Const.AUTH_BODY:
                mDriverAuth.setIdHandImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.xlgcx.http.global.Const.IMG_URL + data, mAuthBody);
                break;
            case Const.AUTH_DRIVER_CARD:
                mDriverAuth.setDriverImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.xlgcx.http.global.Const.IMG_URL + data, mAuthDriverCard);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFilePresenter != null) {
            mFilePresenter.detachView();
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        if (result.getImage() == null) {
            return;
        }
        mFilePresenter.uploadFile(new File(result.getImage().getOriginalPath()));
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.d(getClass().getSimpleName(), msg);
    }

    @Override
    public void takeCancel() {
        Log.d(getClass().getSimpleName(), "takeCancel");
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public void getPhoto(int choseCode) {
        this.choseCode = choseCode;
        getTakePhoto().onPickFromGallery();
    }


    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }
}
