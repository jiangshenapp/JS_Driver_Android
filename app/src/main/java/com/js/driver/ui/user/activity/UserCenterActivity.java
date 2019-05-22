package com.js.driver.ui.user.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import com.js.driver.manager.UserManager;
import com.js.driver.model.event.UserStatusChangeEvent;
import com.js.driver.presenter.FilePresenter;
import com.js.driver.presenter.contract.FileContract;
import com.js.driver.ui.user.presenter.UserCenterPresenter;
import com.js.driver.ui.user.presenter.contract.UserCenterContract;
import com.xlgcx.frame.view.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 */
public class UserCenterActivity extends BaseActivity<UserCenterPresenter> implements UserCenterContract.View, FileContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_cache)
    TextView tvCache;

    @Inject
    FilePresenter mFilePresenter;

    private int choseCode;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private String avatar;
    private String nickname;

    public static void action(Context context) {
        context.startActivity(new Intent(context, UserCenterActivity.class));
    }

    @Override
    protected void init() {
        mFilePresenter.attachView(this);
        initView();
    }

    private void initView() {
        CommonGlideImageLoader.getInstance().displayNetImageWithCircle(mContext, com.xlgcx.http.global.Const.IMG_URL + App.getInstance().avatar
                , ivHead, mContext.getResources().getDrawable(R.mipmap.ic_center_shipper_head_land));
        tvNick.setText(App.getInstance().nickName);

        initVersion();
    }

    private void initVersion() {
        PackageManager manager = mContext.getPackageManager();
        String name = "";
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tvVersion.setText(String.format("V%s", name));
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
        return R.layout.activity_user_center;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("用户中心");
    }


    @OnClick({R.id.center_avatar_layout, R.id.center_name_layout, R.id.center_verified_layout, R.id.center_campus_layout, R.id.center_feedback_layout, R.id.center_version_layout, R.id.center_about_layout, R.id.center_cache_layout, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.center_avatar_layout://头像
                getPhoto(Const.UPLOAD_HEADIMG);
                break;
            case R.id.center_name_layout://昵称
                changeNickname();
                break;
            case R.id.center_verified_layout://实名认证
                UserVerifiedActivity.action(mContext);
                break;
            case R.id.center_campus_layout://园区地址
                break;
            case R.id.center_feedback_layout://意见反馈
                break;
            case R.id.center_version_layout://版本号
                break;
            case R.id.center_about_layout://关于
                break;
            case R.id.center_cache_layout://清除缓存
                break;
            case R.id.logout://登出
                UserManager.getUserManager().logout();
                break;
        }
    }

    public void changeNickname() {
        final EditText inputServer = new EditText(this);
        inputServer.setText(tvNick.getText());
        inputServer.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改昵称").setIcon(null).setView(inputServer)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String nick = inputServer.getText().toString();
                if(nick!=null && !nick.isEmpty()) {
                    nickname = nick;
                    mPresenter.changeNickname(nickname);
                }
                else {
                    toast("请输入昵称");
                }
            }
        });
        builder.show();
    }

    @Override
    public void onUploadFile(String data) {
        Log.d(getClass().getSimpleName(), data);
        switch (choseCode) {
            case Const.UPLOAD_HEADIMG:
                avatar = data;
                mPresenter.changeAvatar(data);
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

    @Override
    public void onChangeAvatar() {
        toast("头像修改成功");
        CommonGlideImageLoader.getInstance().displayNetImageWithCircle(mContext, com.xlgcx.http.global.Const.IMG_URL + avatar, ivHead);
        EventBus.getDefault().post(new UserStatusChangeEvent(UserStatusChangeEvent.CHANGE_SUCCESS));
    }

    @Override
    public void onChangeNickname() {
        toast("昵称修改成功");
        tvNick.setText(nickname);
        EventBus.getDefault().post(new UserStatusChangeEvent(UserStatusChangeEvent.CHANGE_SUCCESS));
    }
}
