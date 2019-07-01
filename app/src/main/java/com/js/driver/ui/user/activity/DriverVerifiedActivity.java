package com.js.driver.ui.user.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import com.js.driver.model.bean.AuthInfo;
import com.js.driver.model.bean.ShengBean;
import com.js.driver.model.event.UserStatusChangeEvent;
import com.js.driver.presenter.FilePresenter;
import com.js.driver.presenter.contract.FileContract;
import com.js.driver.ui.main.activity.MainActivity;
import com.js.driver.ui.user.presenter.DriverVerifiedPresenter;
import com.js.driver.ui.user.presenter.contract.DriverVerifiedContract;
import com.js.driver.util.GetJsonDataUtil;
import com.js.frame.view.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 */
public class DriverVerifiedActivity extends BaseActivity<DriverVerifiedPresenter> implements DriverVerifiedContract.View,
        FileContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.ll_driver)
    LinearLayout llDriver;
    @BindView(R.id.tv_auth_state)
    TextView tvAuthState;
    @BindView(R.id.auth_card)
    ImageView mAuthCard;
    @BindView(R.id.auth_body)
    ImageView mAuthBody;
    @BindView(R.id.auth_driver_card)
    ImageView mAuthDriverCard;
    @BindView(R.id.auth_driver_work)
    ImageView mAuthDriverWork;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_arrow_address)
    ImageView ivArrowAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.et_driver_license)
    EditText etDriverLicense;
    @BindView(R.id.iv_arrow_driver_license)
    ImageView ivArrowDriverLicense;
    @BindView(R.id.ll_driver_license)
    LinearLayout llDriverLicense;
    @BindView(R.id.cb_select)
    CheckBox cbSelect;
    @BindView(R.id.tv_protocal)
    TextView tvProtocal;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.auth_submit)
    TextView mAuthSubmit;

    @Inject
    FilePresenter mFilePresenter;

    private int choseCode;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private AuthInfo mAuthInfo;
    private int authState;

    // 省
    private List<ShengBean> options1Items = new ArrayList<ShengBean>();
    // 市
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    // 区
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    // 驾驶证类型
    private List<String> driverLicenseItems = new ArrayList<String>(Arrays.asList("A1", "A2", "A3", "B1", "B2", "C1", "C2", "C3", "C4", "D", "E", "F", "M", "N", "P"));


    public static void action(Context context) {
        context.startActivity(new Intent(context, DriverVerifiedActivity.class));
    }

    @Override
    protected void init() {
        mFilePresenter.attachView(this);
        mAuthInfo = new AuthInfo();
        authState = App.getInstance().driverVerified;
        if (authState == 0) { //未认证
            tvAuthState.setVisibility(View.GONE);
        } else {
            initAuthData();
            tvAuthState.setText(Const.AuthStateStr[authState]);
            tvAuthState.setTextColor(Color.parseColor(Const.AuthStateColor[authState]));
            if (authState != 3) { //认证失败
                initAuthView();
            }
        }
    }

    public void initAuthData() {
        mPresenter.getDriverVerifiedInfo();
    }

    @Override
    public void onDriverVerifiedInfo(AuthInfo authInfo) {

        mAuthInfo = authInfo;

        CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + authInfo.getIdImage()
                , mAuthCard, mContext.getResources().getDrawable(R.mipmap.img_authentication_id));
        CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + authInfo.getIdHandImage()
                , mAuthBody, mContext.getResources().getDrawable(R.mipmap.img_authentication_body));
        CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + authInfo.getDriverImage()
                , mAuthDriverCard, mContext.getResources().getDrawable(R.mipmap.img_authentication_driver));
        CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + authInfo.getCyzgzImage()
                , mAuthDriverWork, mContext.getResources().getDrawable(R.mipmap.img_authentication_id));
        etName.setText(authInfo.getPersonName());
        etIdcard.setText(authInfo.getIdCode());
        etAddress.setText(authInfo.getAddress());
        etDriverLicense.setText(authInfo.getDriverLevel());
    }

    @Override
    public void onSubmitDriverVerified() {
        toast("提交成功，请耐心等待审核");
        EventBus.getDefault().post(new UserStatusChangeEvent(UserStatusChangeEvent.CHANGE_SUCCESS));
        MainActivity.action(mContext);
    }

    public void initAuthView() {
        mAuthCard.setClickable(false);
        mAuthBody.setClickable(false);
        mAuthDriverCard.setClickable(false);
        mAuthDriverWork.setClickable(false);
        etName.setFocusable(false);
        etIdcard.setFocusable(false);
        llAddress.setClickable(false);
        etAddress.setEnabled(false);
        llDriverLicense.setClickable(false);
        etDriverLicense.setEnabled(false);
        llBottom.setVisibility(View.GONE);
        ivArrowAddress.setVisibility(View.GONE);
        ivArrowDriverLicense.setVisibility(View.GONE);
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

    @OnClick({R.id.auth_card, R.id.auth_body, R.id.auth_driver_card, R.id.auth_driver_work, R.id.auth_submit, R.id.ll_address, R.id.et_address, R.id.ll_driver_license, R.id.et_driver_license, R.id.cb_select, R.id.tv_protocal})
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
            case R.id.auth_driver_work:
                getPhoto(Const.AUTH_DRIVER_WORK);
                break;
            case R.id.ll_address:
            case R.id.et_address:
                // 解析数据
                parseAddressData();
                // 展示省市区选择器
                showAddressPickerView();
                break;
            case R.id.ll_driver_license:
            case R.id.et_driver_license:
                // 展示驾驶证选择器
                showDriverLicensePickerView();
                break;
            case R.id.cb_select:
                cbSelect.setChecked(cbSelect.isChecked());
                break;
            case R.id.tv_protocal:
                toast("用户协议");
                break;
            case R.id.auth_submit:
                submitAction();
                break;
        }
    }

    /**
     * 解析省市区数据并组装成自己想要的list
     */
    private void parseAddressData() {
        String jsonStr = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        // 数据解析
        Gson gson = new Gson();
        Type type = new TypeToken<List<ShengBean>>() {
        }.getType();
        List<ShengBean> shengList = gson.fromJson(jsonStr, type);
        // 把解析后的数据组装成想要的list
        options1Items = shengList;
        // 遍历省
        for (int i = 0; i < shengList.size(); i++) {
            // 存放城市
            ArrayList<String> cityList = new ArrayList<>();
            // 存放区
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();
            // 遍历市
            for (int c = 0; c < shengList.get(i).city.size(); c++) {
                // 拿到城市名称
                String cityName = shengList.get(i).city.get(c).name;
                cityList.add(cityName);

                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                if (shengList.get(i).city.get(c).area == null || shengList.get(i).city.get(c).area.size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(shengList.get(i).city.get(c).area);
                }
                province_AreaList.add(city_AreaList);
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
    }

    /**
     * 展示省市区地址选择器
     */
    private void showAddressPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).name +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                etAddress.setText(tx);
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /**
     * 展示驾驶证选择器
     */
    private void showDriverLicensePickerView() {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = driverLicenseItems.get(options1);
                etDriverLicense.setText(tx);
            }
        }).build();
        pvOptions.setPicker(driverLicenseItems);
        pvOptions.show();
    }

    /**
     * 提交审核
     */
    public void submitAction() {

        String name = etName.getText().toString().trim();
        String idcard = etIdcard.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String driverLicense = etDriverLicense.getText().toString().trim();

        if (TextUtils.isEmpty(mAuthInfo.getIdImage())) {
            toast("请上传司机本人真实身份证");
            return;
        }
        if (TextUtils.isEmpty(mAuthInfo.getIdHandImage())) {
            toast("请上传司机本人手持身份证照片");
            return;
        }
        if (TextUtils.isEmpty(mAuthInfo.getDriverImage())) {
            toast("请上传司机本人驾驶证正本照片");
            return;
        }
        if (TextUtils.isEmpty(name)) {
            toast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(idcard)) {
            toast("请输入身份证号");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            toast("请选择所在区域");
            return;
        }
        if (TextUtils.isEmpty(driverLicense)) {
            toast("请选择驾驶证类型");
            return;
        }
        if (cbSelect.isChecked() == false) {
            toast("请勾选用户协议");
            return;
        }

        mPresenter.submitDriverVerified(mAuthInfo.getIdImage(), mAuthInfo.getIdHandImage(), mAuthInfo.getDriverImage(), mAuthInfo.getCyzgzImage(), name, idcard, address, driverLicense);
    }

    @Override
    public void onUploadFile(String data) {
        Log.d(getClass().getSimpleName(), data);
        switch (choseCode) {
            case Const.AUTH_CARD:
                mAuthInfo.setIdImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, mAuthCard);
                break;
            case Const.AUTH_BODY:
                mAuthInfo.setIdHandImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, mAuthBody);
                break;
            case Const.AUTH_DRIVER_CARD:
                mAuthInfo.setDriverImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, mAuthDriverCard);
                break;
            case Const.AUTH_DRIVER_WORK:
                mAuthInfo.setCyzgzImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, mAuthDriverWork);
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
