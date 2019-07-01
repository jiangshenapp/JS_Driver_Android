package com.js.driver.ui.center.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.global.Const;
import com.js.driver.model.bean.DictBean;
import com.js.driver.model.bean.RouteBean;
import com.js.driver.model.event.CitySelectEvent;
import com.js.driver.model.event.DictSelectEvent;
import com.js.driver.presenter.DictPresenter;
import com.js.driver.presenter.contract.DictContract;
import com.js.driver.ui.center.presenter.AddRoutePresenter;
import com.js.driver.ui.center.presenter.contract.AddRouteContract;
import com.js.driver.widget.window.CityWindow;
import com.js.driver.widget.window.ItemWindow;
import com.js.frame.view.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/12
 * desc   : 添加路线
 * version: 3.0.0
 */
public class AddRouteActivity extends BaseActivity<AddRoutePresenter> implements AddRouteContract.View, DictContract.View {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.tv_address_start)
    TextView tvAddressStart;
    @BindView(R.id.tv_address_end)
    TextView tvAddressEnd;
    @BindView(R.id.et_car_length)
    EditText etCarLength;
    @BindView(R.id.iv_arrow_car_length)
    ImageView ivArrowCarLength;
    @BindView(R.id.ll_car_length)
    LinearLayout llCarLength;
    @BindView(R.id.et_car_model)
    EditText etCarModel;
    @BindView(R.id.iv_arrow_car_model)
    ImageView ivArrowCarModel;
    @BindView(R.id.ll_car_model)
    LinearLayout llCarModel;
    @BindView(R.id.et_car_desc)
    EditText etCarDesc;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Inject
    DictPresenter mDictPresenter;

    private CityWindow mStartWindow;
    private CityWindow mEndWindow;
    private String startAddressCode;
    private String endAddressCode;

    private ItemWindow mTypeWindow;
    private ItemWindow mLengthWindow;
    private String typeStr;
    private String lengthStr;

    private RouteBean mRouteBean;
    private int mType; //1、添加路线  2、编辑路线

    public static void action(Context context, int type) {
        Intent intent = new Intent(context, AddRouteActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public static void action(Context context, int type, RouteBean routeBean) {
        Intent intent = new Intent(context, AddRouteActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("routeBean", routeBean);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        initIntent();
        mDictPresenter.attachView(this);
        initView();
        initData();
    }

    private void initIntent() {
        mType = getIntent().getIntExtra("type", 0);
        if (mType == 2) {
            mRouteBean = getIntent().getParcelableExtra("routeBean");
        }
    }

    private void initData() {
        mDictPresenter.getDictByType(Const.DICT_CAR_TYPE_NAME);
        mDictPresenter.getDictByType(Const.DICT_LENGTH_NAME);

        if (mType == 2) {
            tvAddressStart.setText(mRouteBean.getStartAddressCodeName());
            tvAddressEnd.setText(mRouteBean.getArriveAddressCodeName());
            etCarLength.setText(mRouteBean.getCarLengthName());
            etCarModel.setText(mRouteBean.getCarModelName());
            etCarDesc.setText(mRouteBean.getRemark());
        }
    }

    private void initView() {
        mTypeWindow = new ItemWindow(mContext, Const.DICT_CAR_TYPE);
        mLengthWindow = new ItemWindow(mContext, Const.DICT_LENGTH);
        mStartWindow = new CityWindow(mContext, 0);
        mEndWindow = new CityWindow(mContext, 1);
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
        return R.layout.activity_add_route;
    }

    @Override
    public void setActionBar() {
        if (mType == 1) {
            mTitle.setText("添加路线");
        } else {
            mTitle.setText("编辑路线");
        }
    }

    @Override
    public void onAddLine() {
        toast("保存成功");
        finish();
    }

    @Override
    public void onEditLine() {
        toast("保存成功");
        finish();
    }

    @OnClick({R.id.tv_address_start, R.id.tv_address_end, R.id.ll_car_length, R.id.et_car_length,
            R.id.ll_car_model, R.id.et_car_model, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address_start:
                mStartWindow.showAsDropDown(tvTop, 0, 0);
                break;
            case R.id.tv_address_end:
                mEndWindow.showAsDropDown(tvTop, 0, 0);
                break;
            case R.id.ll_car_length:
            case R.id.et_car_length://车长
                mLengthWindow.showAsDropDown(tvTop, 0, 0);
                break;
            case R.id.ll_car_model:
            case R.id.et_car_model://车型
                mTypeWindow.showAsDropDown(tvTop, 0, 0);
                break;
            case R.id.tv_submit:
                if (TextUtils.isEmpty(tvAddressStart.getText())
                || tvAddressStart.getText().equals("+选择")) {
                    toast("请选择出发地");
                    return;
                }
                if (TextUtils.isEmpty(tvAddressEnd.getText())
                        || tvAddressEnd.getText().equals("+选择")) {
                    toast("请选择目的地");
                    return;
                }
                if (TextUtils.isEmpty(etCarLength.getText())) {
                    toast("请选择车长");
                    return;
                }
                if (TextUtils.isEmpty(etCarModel.getText())) {
                    toast("请选择车型");
                    return;
                }
                if (TextUtils.isEmpty(etCarDesc.getText())) {
                    toast("请输入简介");
                    return;
                }

                if (mType == 1) { //添加
                    mPresenter.addLine(endAddressCode, lengthStr, typeStr, startAddressCode, etCarDesc.getText().toString());
                } else { //编辑
                    mPresenter.editLine(endAddressCode, lengthStr, typeStr, startAddressCode, etCarDesc.getText().toString()
                    ,mRouteBean.getId(),mRouteBean.getState(),mRouteBean.getClassic());
                }

                break;
        }
    }

    @Subscribe
    public void onEvent(CitySelectEvent event) {
        switch (event.type) {
            case 0:
                if (!TextUtils.isEmpty(event.areaBean.getAlias())) {
                    tvAddressStart.setText(event.areaBean.getAlias());
                } else {
                    tvAddressStart.setText(event.areaBean.getName());
                }
                startAddressCode = event.areaBean.getCode();
                break;
            case 1:
                if (!TextUtils.isEmpty(event.areaBean.getAlias())) {
                    tvAddressEnd.setText(event.areaBean.getAlias());
                } else {
                    tvAddressEnd.setText(event.areaBean.getName());
                }
                endAddressCode = event.areaBean.getCode();
                break;
        }
    }

    @Subscribe
    public void onEvent(DictSelectEvent event) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        List<DictBean> dictBeans = event.mDicts;
        for (DictBean dictBean : dictBeans) {
            builder.append(dictBean.getLabel());
            builder.append(",");
            builder1.append(dictBean.getValue());
            builder1.append(",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        if (builder1.length() > 0) {
            builder1.deleteCharAt(builder1.length() - 1);
        }
        switch (event.type) {
            case Const.DICT_LENGTH:
                etCarLength.setText(builder.toString());
                lengthStr = builder1.toString();
                break;
            case Const.DICT_CAR_TYPE:
                etCarModel.setText(builder.toString());
                typeStr = builder1.toString();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDictPresenter != null) {
            mDictPresenter.detachView();
        }
    }

    @Override
    public void onDictByType(String type, List<DictBean> dictBeans) {
        switch (type) {
            case Const.DICT_CAR_TYPE_NAME:
                mTypeWindow.setData(dictBeans);
                break;
            case Const.DICT_LENGTH_NAME:
                mLengthWindow.setData(dictBeans);
                break;
        }
    }
}
