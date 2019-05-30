package com.js.driver.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.manager.CommonGlideImageLoader;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.ui.order.presenter.OrderDetailPresenter;
import com.js.driver.ui.order.presenter.contract.OrderDetailContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xlgcx.frame.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailContract.View {


    @BindView(R.id.detail_avatar)
    ImageView mAvatar;
    @BindView(R.id.detail_send_name)
    TextView mSendName;
    @BindView(R.id.detail_send_introduce)
    TextView mSendIntroduce;
    @BindView(R.id.detail_order_number)
    TextView mOrderNumber;
    @BindView(R.id.detail_send_address)
    TextView mSendAddress;
    @BindView(R.id.detail_send_city)
    TextView mSendCity;
    @BindView(R.id.detail_send_distance)
    TextView mSendDistance;
    @BindView(R.id.detail_arrive_address)
    TextView mArriveAddress;
    @BindView(R.id.detail_arrive_city)
    TextView mArriveCity;
    @BindView(R.id.detail_arrive_distance)
    TextView mArriveDistance;
    @BindView(R.id.detail_loading_time)
    TextView mLoadingTime;
    @BindView(R.id.detail_car_info)
    TextView mCarInfo;
    @BindView(R.id.detail_goods_type)
    TextView mGoodsType;
    @BindView(R.id.detail_car_use_type)
    TextView mCarUseType;
    @BindView(R.id.detail_pay_type)
    TextView mPayType;
    @BindView(R.id.detail_pay_money)
    TextView mPayMoney;
    @BindView(R.id.detail_pay_method)
    TextView mPayMethod;
    @BindView(R.id.detail_order_remark)
    TextView mOrderRemark;
    @BindView(R.id.detail_arrive_name)
    TextView mArriveName;
    @BindView(R.id.detail_arrive_phone)
    TextView mArrivePhone;
    @BindView(R.id.detail_img1)
    ImageView mImg1;
    @BindView(R.id.detail_img2)
    ImageView mImg2;
    @BindView(R.id.detail_img3)
    ImageView mImg3;
    @BindView(R.id.detail_order_navigate)
    TextView mOrderNavigate;
    @BindView(R.id.detail_order_positive)
    TextView mOrderPositive;
    @BindView(R.id.detail_order_finish)
    TextView mOrderFinish;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;


    @OnClick({R.id.detail_send_phone, R.id.detail_send_wechat, R.id.detail_send_navigate, R.id.detail_arrive_navigate, R.id.detail_img1_layout, R.id.detail_img2_layout, R.id.detail_img3_layout, R.id.detail_order_navigate, R.id.detail_order_positive, R.id.detail_order_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detail_send_phone://打电话
                break;
            case R.id.detail_send_wechat://微信
                break;
            case R.id.detail_send_navigate://发货地址导航
                break;
            case R.id.detail_arrive_navigate://到货地址导航
                break;
            case R.id.detail_img1_layout:
                break;
            case R.id.detail_img2_layout:
                break;
            case R.id.detail_img3_layout:
                break;
            case R.id.detail_order_navigate:
                break;
            case R.id.detail_order_positive:
                break;
            case R.id.detail_order_finish:
                break;
        }
    }


    private long orderId;

    public static void action(Context context, long orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }


    @Override
    protected void init() {
        initIntent();
        iniView();
    }

    private void initIntent() {
        orderId = getIntent().getLongExtra("orderId", 0);
    }

    private void iniView() {
        initRefresh();
    }

    private void initRefresh() {
        mRefresh.autoRefresh();
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getOrderDetail(orderId);
            }
        });
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
        return R.layout.activity_order_detail;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("订单详情");
    }

    @Override
    public void onOrderDetail(OrderBean orderBean) {
        if (orderBean != null) {
//            CommonGlideImageLoader.getInstance().displayNetImageWithCircle(mContext,orderBean.get);
            mSendName.setText(orderBean.getSendName());
            mOrderNumber.setText(orderBean.getOrderNo());
            mSendAddress.setText(orderBean.getSendAddress());

        }
    }

    @Override
    public void finishRefresh() {
        mRefresh.finishRefresh();
    }


}
