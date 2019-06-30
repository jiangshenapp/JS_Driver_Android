package com.js.driver.ui.center.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
import com.js.driver.model.bean.CarBean;
import com.js.driver.model.bean.DictBean;
import com.js.driver.presenter.DictPresenter;
import com.js.driver.presenter.FilePresenter;
import com.js.driver.presenter.contract.DictContract;
import com.js.driver.presenter.contract.FileContract;
import com.js.driver.ui.center.presenter.AddCarPresenter;
import com.js.driver.ui.center.presenter.contract.AddCarContract;
import com.xlgcx.frame.view.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/29.
 */
public class AddCarActivity extends BaseActivity<AddCarPresenter> implements AddCarContract.View, DictContract.View, FileContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.tv_auth_state)
    TextView tvAuthState;
    @BindView(R.id.et_car_no)
    EditText etCarNo;
    @BindView(R.id.et_car_model)
    EditText etCarModel;
    @BindView(R.id.iv_arrow_car_model)
    ImageView ivArrowCarModel;
    @BindView(R.id.ll_car_model)
    LinearLayout llCarModel;
    @BindView(R.id.et_trade_no)
    EditText etTradeNo;
    @BindView(R.id.et_transport_no)
    EditText etTransportNo;
    @BindView(R.id.et_car_length)
    EditText etCarLength;
    @BindView(R.id.iv_arrow_car_length)
    ImageView ivArrowCarLength;
    @BindView(R.id.ll_car_length)
    LinearLayout llCarLength;
    @BindView(R.id.et_car_weight)
    EditText etCarWeight;
    @BindView(R.id.et_car_volume)
    EditText etCarVolume;
    @BindView(R.id.iv_car_license)
    ImageView ivCarLicense;
    @BindView(R.id.iv_car_head)
    ImageView ivCarHead;
    @BindView(R.id.tv_car_license)
    TextView tvCarLicense;
    @BindView(R.id.iv_car_license_tip)
    ImageView ivCarLicenseTip;
    @BindView(R.id.tv_car_head)
    TextView tvCarHead;
    @BindView(R.id.iv_car_head_tip)
    ImageView ivCarHeadTip;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Inject
    DictPresenter mDictPresenter;

    List<DictBean> mCarModelBeans;
    List<DictBean> mCarLengthBeans;
    DictBean mCarModelBean;
    DictBean mCarLengthBean;

    @Inject
    FilePresenter mFilePresenter;

    private int choseCode;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private CarBean mCarBean;
    private int authState;
    private long mId;
    private int mType; //1、添加车辆  2、车辆详情

    public static void action(Context context, int type, long id) {
        Intent intent = new Intent(context, AddCarActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        initIntent();
        mDictPresenter.attachView(this);
        mFilePresenter.attachView(this);
        initData();
    }

    private void initIntent() {
        mType = getIntent().getIntExtra("type", 0);
        mId = getIntent().getLongExtra("id", 0);
    }

    private void initData() {
        mCarBean = new CarBean();
        mDictPresenter.getDictByType(Const.DICT_CAR_TYPE_NAME);
        mDictPresenter.getDictByType(Const.DICT_LENGTH_NAME);
        if (mType == 2) {
            mPresenter.getCarDetail(mId);
        }
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
        return R.layout.activity_add_car;
    }

    @Override
    public void setActionBar() {
        if (mType == 1) {
            mTitle.setText("添加车辆");
        } else {
            mTitle.setText("车辆详情");
        }
    }

    @Override
    public void onCarDetail(CarBean carBean) {
        authState = carBean.getState(); //车辆状态，0待审核，1通过，2拒绝，3审核中
        if (authState == 2) {
            tvSubmit.setText("重新提交");
        } else {
            changeView();
            if (authState == 1) {
                tvSubmit.setText("解绑");
                tvSubmit.setBackgroundColor(getResources().getColor(R.color._D0021B));
            } else {
                tvSubmit.setVisibility(View.GONE);
            }
        }
        tvCarLicense.setText("车辆行驶证");
        tvCarHead.setText("车头照");
        ivCarLicenseTip.setVisibility(View.GONE);
        ivCarHeadTip.setVisibility(View.GONE);
        tvAuthState.setVisibility(View.VISIBLE);
        tvAuthState.setText(carBean.getStateName());
        tvAuthState.setTextColor(Color.parseColor(Const.CarStateColor[authState]));
        etCarNo.setText(carBean.getCphm());
        etCarModel.setText(carBean.getCarModelName());
        etTradeNo.setText(carBean.getTradingNo());
        etTransportNo.setText(carBean.getTransportNo());
        etCarLength.setText(carBean.getCarLengthName());
        etCarWeight.setText(String.valueOf(carBean.getCapacityTonnage()));
        etCarVolume.setText(String.valueOf(carBean.getCapacityVolume()));
        CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.xlgcx.http.global.Const.IMG_URL + carBean.getImage1()
                , ivCarLicense, mContext.getResources().getDrawable(R.mipmap.ic_car_auth_travel));
        CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.xlgcx.http.global.Const.IMG_URL + carBean.getImage2()
                , ivCarHead, mContext.getResources().getDrawable(R.mipmap.ic_car_auth_header));
    }

    public void changeView() {

        etCarNo.setFocusable(false);
        etCarWeight.setFocusable(false);
        etCarVolume.setFocusable(false);
        etTradeNo.setFocusable(false);
        etTransportNo.setFocusable(false);
        llCarModel.setClickable(false);
        etCarModel.setEnabled(false);
        llCarLength.setClickable(false);
        etCarLength.setEnabled(false);
        ivArrowCarModel.setVisibility(View.GONE);
        ivArrowCarLength.setVisibility(View.GONE);
        ivCarLicense.setClickable(false);
        ivCarHead.setClickable(false);
    }

    @Override
    public void onBindingCar() {
        toast("提交审核成功，请等待审核结果");
        finish();
    }

    @Override
    public void onReBindingCar() {
        toast("重新提交成功，请等待审核结果");
        finish();
    }

    @Override
    public void onUnbindingCar() {
        toast("解绑成功");
        finish();
    }

    @OnClick({R.id.ll_car_model, R.id.et_car_model, R.id.ll_car_length, R.id.et_car_length, R.id.iv_car_license, R.id.iv_car_head, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_car_model:
            case R.id.et_car_model: //选择车型
                if (mCarModelBeans != null) {
                    showCarModelPickerView(mCarModelBeans);
                }
                break;
            case R.id.ll_car_length:
            case R.id.et_car_length: //选择车长
                if (mCarLengthBeans != null) {
                    showCarLengthPickerView(mCarLengthBeans);
                }
                break;
            case R.id.iv_car_license: //上传车辆行驶证
                getPhoto(Const.AUTH_CAR_LICENSE);
                break;
            case R.id.iv_car_head: //上传车头照
                getPhoto(Const.AUTH_CAR_HEAD);
                break;
            case R.id.tv_submit: //提交审核/解绑车辆
                submitAction();
                break;
        }
    }

    /**
     * 提交审核/解绑车辆
     */
    public void submitAction() {
        if (tvSubmit.getText().toString().equals("解绑")) {
            mPresenter.unbindingCar(mCarBean.getId());
            return;
        }

        String carNo = etCarNo.getText().toString();
        String carModel = etCarModel.getText().toString();
        String tradeNo = etTradeNo.getText().toString();
        String transportNo = etTransportNo.getText().toString();
        String carLength = etCarLength.getText().toString();
        String carWeight = etCarWeight.getText().toString();
        String carVolume = etCarVolume.getText().toString();

        if (TextUtils.isEmpty(carNo)) {
            toast("请输入车牌号码");
            return;
        }
        if (TextUtils.isEmpty(carModel)) {
            toast("请选择车型");
            return;
        }
        if (TextUtils.isEmpty(carLength)) {
            toast("请选择车长");
            return;
        }
        if (TextUtils.isEmpty(carWeight)) {
            toast("请输入载货重量");
            return;
        }
        if (TextUtils.isEmpty(carVolume)) {
            toast("请输入载货空间");
            return;
        }
        if (TextUtils.isEmpty(mCarBean.getImage1())) {
            toast("请拍照上传车辆行驶证");
            return;
        }
        if (TextUtils.isEmpty(mCarBean.getImage2())) {
            toast("请拍照上传车头照");
            return;
        }

        if (tvSubmit.getText().toString().equals("提交审核")) {
            mPresenter.bindingCar(mCarBean.getImage1(), String.valueOf(mCarModelBean.getId()), mCarBean.getImage2(),
                    carVolume, "0", String.valueOf(mCarLengthBean.getId()), carNo, carWeight, tradeNo, transportNo);
        }
        if (tvSubmit.getText().toString().equals("重新提交")) {
            mPresenter.reBindingCar(mCarBean.getId(), mCarBean.getImage1(), String.valueOf(mCarModelBean.getId()), mCarBean.getImage2(),
                    carVolume, "2", String.valueOf(mCarLengthBean.getId()), carNo, carWeight, tradeNo, transportNo);
        }
    }

    @Override
    public void onDictByType(String type, List<DictBean> dictBeans) {
        switch (type) {
            case Const.DICT_CAR_TYPE_NAME:
                mCarModelBeans = dictBeans;
                break;
            case Const.DICT_LENGTH_NAME:
                mCarLengthBeans = dictBeans;
                break;
        }
    }

    /**
     * 展示车型选择器
     */
    public void showCarModelPickerView(List<DictBean> dictBeans) {
        List<String> carModelItems = new ArrayList<String>();
        for (int i = 0; i < dictBeans.size(); i++) {
            carModelItems.add(dictBeans.get(i).getLabel());
        }

        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mCarModelBean = dictBeans.get(options1);
                String tx = carModelItems.get(options1);
                etCarModel.setText(tx);
            }
        }).build();
        pvOptions.setPicker(carModelItems);
        pvOptions.show();
    }

    /**
     * 展示车长选择器
     */
    public void showCarLengthPickerView(List<DictBean> dictBeans) {
        List<String> carLengthItems = new ArrayList<String>();
        for (int i = 0; i < dictBeans.size(); i++) {
            carLengthItems.add(dictBeans.get(i).getLabel());
        }

        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mCarLengthBean = dictBeans.get(options1);
                String tx = carLengthItems.get(options1);
                etCarLength.setText(tx);
            }
        }).build();
        pvOptions.setPicker(carLengthItems);
        pvOptions.show();
    }

    @Override
    public void onUploadFile(String data) {
        Log.d(getClass().getSimpleName(), data);
        switch (choseCode) {
            case Const.AUTH_CAR_LICENSE:
                mCarBean.setImage1(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.xlgcx.http.global.Const.IMG_URL + data, ivCarLicense);
                break;
            case Const.AUTH_CAR_HEAD:
                mCarBean.setImage2(data);
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.xlgcx.http.global.Const.IMG_URL + data, ivCarHead);
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
