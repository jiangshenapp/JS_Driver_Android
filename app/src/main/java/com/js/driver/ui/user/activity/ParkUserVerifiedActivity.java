package com.js.driver.ui.user.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import com.afollestad.materialdialogs.MaterialDialog;
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
import com.js.driver.ui.user.presenter.ParkUserVerifiedPresenter;
import com.js.driver.ui.user.presenter.contract.ParkUserVerifiedContract;
import com.js.driver.util.GetJsonDataUtil;
import com.js.frame.view.BaseActivity;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 */
public class ParkUserVerifiedActivity extends BaseActivity<ParkUserVerifiedPresenter> implements ParkUserVerifiedContract.View, FileContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.tv_auth_state)
    TextView tvAuthState;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_company_type)
    EditText etCompanyType;
    @BindView(R.id.iv_arrow_company_type)
    ImageView ivArrowCompanyType;
    @BindView(R.id.ll_company_type)
    LinearLayout llCompanyType;
    @BindView(R.id.cb_business_license_have)
    CheckBox cbBusinessLicenseHave;
    @BindView(R.id.cb_business_license_no)
    CheckBox cbBusinessLicenseNo;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_arrow_address)
    ImageView ivArrowAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.et_detail_address)
    EditText etDetailAddress;
    @BindView(R.id.auth_business_license)
    ImageView authBusinessLicense;
    @BindView(R.id.cb_select)
    CheckBox cbSelect;
    @BindView(R.id.tv_protocal)
    TextView tvProtocal;
    @BindView(R.id.auth_submit)
    TextView authSubmit;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ll_park_user)
    LinearLayout llParkUser;

    @Inject
    FilePresenter mFilePresenter;

    private int choseCode;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private AuthInfo mAuthInfo;
    private int authState;
    private String[] items = {"拍摄","从相册选择"};
    private String province;
    private String city;
    private String district;

    // 省
    private List<ShengBean> options1Items = new ArrayList<ShengBean>();
    // 市
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    // 区
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    // 机构类型
    private List<String> companyTypeItems = new ArrayList<String>(Arrays.asList("服务中心","车代点","网点"));


    public static void action(Context context) {
        context.startActivity(new Intent(context, ParkUserVerifiedActivity.class));
    }

    @Override
    protected void init() {
        mFilePresenter.attachView(this);
        mAuthInfo = new AuthInfo();
        authState = App.getInstance().parkVerified;
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
        mPresenter.getParkUserVerifiedInfo();
    }

    @Override
    public void onParkUserVerifiedInfo(AuthInfo authInfo) {
        mAuthInfo = authInfo;

        etName.setText(authInfo.getCompanyName());
        etCompanyType.setText(authInfo.getCompanyType());
        if (TextUtils.isEmpty(mAuthInfo.getBusinessLicenceImage())) {
            cbBusinessLicenseHave.setChecked(false);
            cbBusinessLicenseNo.setChecked(true);
        } else {
            cbBusinessLicenseHave.setChecked(true);
            cbBusinessLicenseNo.setChecked(false);
        }
        etIdcard.setText(authInfo.getRegistrationNumber());
        etAddress.setText(authInfo.getAddress());
        etDetailAddress.setText(authInfo.getDetailAddress());

        CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + authInfo.getBusinessLicenceImage()
                , authBusinessLicense, mContext.getResources().getDrawable(R.mipmap.img_authentication_id));
    }

    @Override
    public void onSubmitParkUserVerified() {
        toast("提交成功，请耐心等待审核");
        EventBus.getDefault().post(new UserStatusChangeEvent(UserStatusChangeEvent.CHANGE_SUCCESS));
        MainActivity.action(mContext);
    }

    public void initAuthView() {

        etName.setFocusable(false);
        etIdcard.setFocusable(false);
        etDetailAddress.setFocusable(false);
        cbBusinessLicenseHave.setClickable(false);
        cbBusinessLicenseNo.setClickable(false);
        llCompanyType.setClickable(false);
        etCompanyType.setEnabled(false);
        llAddress.setClickable(false);
        etAddress.setEnabled(false);
        ivArrowCompanyType.setVisibility(View.GONE);
        ivArrowAddress.setVisibility(View.GONE);
        authBusinessLicense.setClickable(false);
        llBottom.setVisibility(View.GONE);
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
        return R.layout.activity_park_user_verified;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("园区成员认证");
    }

    @OnClick({R.id.ll_company_type, R.id.et_company_type, R.id.cb_business_license_have, R.id.cb_business_license_no, R.id.ll_address, R.id.et_address, R.id.auth_business_license, R.id.cb_select, R.id.tv_protocal, R.id.auth_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_company_type:
            case R.id.et_company_type:
                // 展示机构类型选择器
                showCompanyTypePickerView();
                break;
            case R.id.cb_business_license_have:
                cbBusinessLicenseHave.setChecked(true);
                cbBusinessLicenseNo.setChecked(false);
                break;
            case R.id.cb_business_license_no:
                cbBusinessLicenseHave.setChecked(false);
                cbBusinessLicenseNo.setChecked(true);
                break;
            case R.id.ll_address:
            case R.id.et_address:
                selectAddress();//调用CityPicker选取区域
//                // 解析数据
//                parseAddressData();
//                // 展示省市区选择器
//                showAddressPickerView();
                break;
            case R.id.auth_business_license:
                getPhoto(Const.AUTH_BUSINESS_LICENSE);
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
     * 展示机构类型选择器
     */
    public void showCompanyTypePickerView() {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String tx = companyTypeItems.get(options1);
                etCompanyType.setText(tx);
            }
        }).build();
        pvOptions.setPicker(companyTypeItems);
        pvOptions.show();
    }

    /**
     * 选择地址
     */
    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(ParkUserVerifiedActivity.this)
                .textSize(14)
                .title("选择地址")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province(province)
                .city(city)
                .district(district)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                province = citySelected[0];
                //城市
                city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                etAddress.setText(province.trim() + city.trim() + district.trim() + code.trim());
            }
        });
    }

    /**
     * 解析省市区数据并组装成自己想要的list
     */
    private void parseAddressData() {
        String jsonStr = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        // 数据解析
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<ShengBean>>() {
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
     * 提交审核
     */
    public void submitAction() {

        String name = etName.getText().toString().trim();
        String companyType = etCompanyType.getText().toString().trim();
        String idcard = etIdcard.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String detailAddress = etDetailAddress.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            toast("请输入代理点名称/个人姓名");
            return;
        }
        if (TextUtils.isEmpty(companyType)) {
            toast("请选择机构类型");
            return;
        }
        if (TextUtils.isEmpty(idcard)) {
            toast("请输入证件类型/证件号码");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            toast("请选择所在地");
            return;
        }
        if (TextUtils.isEmpty(detailAddress)) {
            toast("请输入详细地址");
            return;
        }
        if (cbBusinessLicenseHave.isChecked() == true &&
                TextUtils.isEmpty(mAuthInfo.getBusinessLicenceImage())) {
            toast("请上传公司营业执照");
            return;
        }
        if (cbSelect.isChecked() == false) {
            toast("请勾选用户协议");
            return;
        }

        String type = "1";
        if (companyType.equals("服务中心")) {
            type = "1";
        }
        if (companyType.equals("车代点")) {
            type = "2";
        }
        if (companyType.equals("网点")) {
            type = "3";
        }
        mPresenter.submitParkUserVerified(name, type, idcard, address, detailAddress, mAuthInfo.getBusinessLicenceImage());
    }

    @Override
    public void onUploadFile(String data) {
        Log.d(getClass().getSimpleName(), data);
        switch (choseCode) {
            case Const.AUTH_BUSINESS_LICENSE:
                mAuthInfo.setBusinessLicenceImage(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, authBusinessLicense);
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
        showDialog();
    }

    private void showDialog(){
        new MaterialDialog.Builder(mContext)
                .items(items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position==0){
                            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            Uri imageUri = Uri.fromFile(file);
                            getTakePhoto().onPickFromCapture(imageUri);
                        }else {
                            getTakePhoto().onPickFromGallery();
                        }
                    }
                }).show();
    }


    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }
}
