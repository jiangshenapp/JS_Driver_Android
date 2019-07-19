package com.js.driver.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
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
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.request.OrderComment;
import com.js.driver.model.request.OrderDistribution;
import com.js.driver.presenter.FilePresenter;
import com.js.driver.presenter.contract.FileContract;
import com.js.driver.ui.main.activity.MainActivity;
import com.js.driver.ui.order.presenter.OrderDetailPresenter;
import com.js.driver.ui.order.presenter.contract.OrderDetailContract;
import com.js.driver.util.MapUtils;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.params.DialogParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.js.frame.view.BaseActivity;

import java.io.File;
import java.text.MessageFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailContract.View, FileContract.View, InvokeListener, TakePhoto.TakeResultListener {


    @BindView(R.id.detail_avatar)
    ImageView mAvatar;
    @BindView(R.id.detail_send_name)
    TextView mSendName;
    @BindView(R.id.detail_send_introduce)
    TextView mSendIntroduce;
    @BindView(R.id.detail_order_number)
    TextView mOrderNumber;
    @BindView(R.id.detail_order_status)
    TextView mOrderStatus;
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
    @BindView(R.id.detail_goods_name)
    TextView mGoodsName;
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
    TextView mOrderPosition;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.control_layout)
    LinearLayout mControlLayout;
    @BindView(R.id.bail)
    TextView mBail;
    @BindView(R.id.pack_type)
    TextView mPackType;
    @BindView(R.id.receipt_layout)
    LinearLayout mReceiptLayout;
    @BindView(R.id.receipt_title)
    TextView mReceiptTitle;


    @OnClick({R.id.detail_send_phone, R.id.detail_send_wechat, R.id.detail_send_navigate, R.id.detail_arrive_navigate, R.id.detail_img1, R.id.detail_img2, R.id.detail_img3, R.id.detail_order_navigate, R.id.detail_order_positive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detail_send_phone://打电话
                break;
            case R.id.detail_send_wechat://微信
                break;
            case R.id.detail_send_navigate:
                if (App.getInstance().mLocation == null) {
                    toast("定位失败");
                    return;
                }
                Gson gson = new Gson();
                LatLng latLng = new LatLng(App.getInstance().mLocation.getLatitude(), App.getInstance().mLocation.getLongitude());
                showSelectDialog(latLng, gson.fromJson(mOrderBean.getSendPosition(), LatLng.class), mOrderBean.getSendAddress());
                break;
            case R.id.detail_arrive_navigate:
                if (App.getInstance().mLocation == null) {
                    toast("定位失败");
                    return;
                }
                Gson gson1 = new Gson();
                LatLng latLng1 = new LatLng(App.getInstance().mLocation.getLatitude(), App.getInstance().mLocation.getLongitude());
                showSelectDialog(latLng1, gson1.fromJson(mOrderBean.getReceivePosition(), LatLng.class), mOrderBean.getReceiveAddress());
                break;
            case R.id.detail_img1:
                getPhoto(1);
                break;
            case R.id.detail_img2:
                getPhoto(2);
                break;
            case R.id.detail_img3:
                getPhoto(3);
                break;

            case R.id.detail_order_navigate:
                switch (status) {
                    case 2:
                        mPresenter.refuseOrder(orderId);
                        break;
                    case 3:
                        mPresenter.cancelConfirmOrder(orderId);
                        break;
                    case 4:

                        break;
                    case 5:
                        mPresenter.cancelDistribution(orderId);
                        break;

                }
                break;
            case R.id.detail_order_positive:
                switch (status) {
                    case 2:
                        mPresenter.receiveOrder(orderId);
                        break;
                    case 3:
                        if (mOrderBean.getFeeType() == 2) {
                            toast("价格不可为电议 ，请联系货主修改！");
                            return;
                        }
                        mPresenter.confirmOrder(orderId);
                        break;
                    case 5:
                        DistributionActivity.action(OrderDetailActivity.this);
                        break;
                    case 6:
                        mPresenter.completeDistribution(orderId);
                        break;
                    case 7://上传回执
                        if (TextUtils.isEmpty(img2Url) || TextUtils.isEmpty(img2Url) || TextUtils.isEmpty(img2Url)) {
                            toast("请上传图片");
                            return;
                        }
                        OrderComment orderComment = new OrderComment();
                        orderComment.setId(mOrderBean.getId());
                        orderComment.setCommentImage1(img1Url);
                        orderComment.setCommentImage2(img2Url);
                        orderComment.setCommentImage3(img3Url);
                        mPresenter.commentOrder(orderComment);
                        break;
                }
                break;
        }
    }


    private long orderId;
    private int status;
    private int carId;
    private OrderBean mOrderBean;
    private String[] items = {"百度地图", "高德地图"};
    private String[] item = {"拍摄", "从相册选择"};
    @Inject
    FilePresenter mFilePresenter;
    private int choseCode;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private String img1Url;
    private String img2Url;
    private String img3Url;

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
        mFilePresenter.attachView(this);
        initRefresh();
    }

    private void initRefresh() {
        mRefresh.autoRefresh();
        mRefresh.setEnableLoadMore(false);
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
            mOrderBean = orderBean;
            status = orderBean.getState();
//            CommonGlideImageLoader.getInstance().displayNetImageWithCircle(mContext,orderBean.get);
            mSendName.setText(orderBean.getSendName());
            mOrderNumber.setText("订单编号：" + orderBean.getOrderNo());
            mOrderStatus.setText(orderBean.getStateName());
            mSendAddress.setText(orderBean.getSendAddressCodeName());
            mSendCity.setText(orderBean.getSendAddress());
            mArriveAddress.setText(orderBean.getReceiveAddress());
            mArriveCity.setText(orderBean.getReceiveAddressCodeName());
            mLoadingTime.setText(orderBean.getLoadingTime());
            mCarInfo.setText(orderBean.getGoodsVolume() + "方/" + orderBean.getGoodsWeight() + "吨");
            mGoodsName.setText(orderBean.getGoodsName());
            mCarUseType.setText(orderBean.getUseCarType());
            mBail.setText(String.valueOf(orderBean.getDeposit()));
            mPackType.setText(orderBean.getPackType());
            switch (orderBean.getPayWay()) {
                case 1:
                    mPayType.setText("线上支付");
                    break;
                case 2:
                    mPayType.setText("线下支付");
                    break;
            }

            switch (orderBean.getPayType()) {
                case 1:
                    mPayMethod.setText("到付");
                    break;
                case 2:
                    mPayMethod.setText("现付");
                    break;
            }

            switch (orderBean.getFeeType()) {
                case 1:
                    mPayMoney.setText(String.valueOf(orderBean.getFee()));
                    break;
                case 2:
                    mPayMoney.setText("电议");
                    break;
            }

            if (!TextUtils.isEmpty(orderBean.getRemark())) {
                mOrderRemark.setVisibility(View.VISIBLE);
                mOrderRemark.setText(orderBean.getRemark());
            } else {
                mOrderRemark.setVisibility(View.GONE);
            }
            if (status == 2 || status == 3 || status == 4) {
                mArriveName.setText(orderBean.getReceiveName().substring(1) + "xx");
                mArrivePhone.setText(orderBean.getReceiveMobile());
            } else {
                mArriveName.setText(orderBean.getReceiveName());
                mArrivePhone.setText(MessageFormat.format("{0}****{1}", orderBean.getReceiveMobile().substring(0,3), orderBean.getReceiveMobile().substring(orderBean.getReceiveMobile().length() - 4)));
            }


            //2待接单，3待确认，4待货主付款，5待接货, 6待送达，7待确认收货，8待回单收到确认，9待货主评价，10已完成，11取消，12已关闭
            mControlLayout.setVisibility(View.VISIBLE);
            mReceiptLayout.setVisibility(View.GONE);
            mReceiptTitle.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(mOrderBean.getCommentImage1())) {
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + mOrderBean.getCommentImage1(), mImg1);
            }
            if (!TextUtils.isEmpty(mOrderBean.getCommentImage2())) {
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + mOrderBean.getCommentImage2(), mImg2);
            }
            if (!TextUtils.isEmpty(mOrderBean.getCommentImage3())) {
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + mOrderBean.getCommentImage3(), mImg3);
            }
            switch (status) {
                case 2:
                    mOrderPosition.setText("立即接单");
                    mOrderNavigate.setText("拒绝接单");
                    break;
                case 3:
                    mOrderPosition.setText("立即确认");
                    mOrderNavigate.setText("拒绝接单");
                    break;
                case 4:
                    mOrderPosition.setText("等待货主支付");
                    mOrderNavigate.setText("取消接货");
                    break;
                case 5:
                    mOrderPosition.setText("开始配送");
                    mOrderNavigate.setText("拒绝配送");
                    break;
                case 6:
                    mOrderNavigate.setVisibility(View.GONE);
                    mOrderPosition.setText("我已送达");
                    break;
                case 7:
                case 8:
                    mOrderNavigate.setVisibility(View.GONE);
                    mOrderPosition.setText("上传回执");
                    mReceiptLayout.setVisibility(View.VISIBLE);
                    mReceiptTitle.setVisibility(View.VISIBLE);
                    mImg1.setClickable(true);
                    mImg2.setClickable(true);
                    mImg3.setClickable(true);
                    break;
                case 9:
                case 10:
                    mReceiptLayout.setVisibility(View.VISIBLE);
                    mReceiptTitle.setVisibility(View.VISIBLE);
                    mControlLayout.setVisibility(View.GONE);
                    mImg1.setClickable(false);
                    mImg2.setClickable(false);
                    mImg3.setClickable(false);
                    break;
                case 11:
                    break;
            }
        }
    }

    @Override
    public void finishRefresh() {
        mRefresh.finishRefresh();
    }

    @Override
    public void onReceiveOrder(boolean isOk) {
        if (isOk) {
            OrdersActivity.action(mContext, 0);
            toast("接单成功");
            finish();
        } else {
            toast("接单失败");
        }
    }

    @Override
    public void onRefuseOrder(boolean isOk) {
        if (isOk) {
            toast("拒绝成功");
            MainActivity.action(mContext);
            finish();
        } else {
            toast("拒绝失败");
        }

    }

    @Override
    public void onConfirmOrder(boolean isOk) {
        if (isOk) {
            mPresenter.getOrderDetail(orderId);
            toast("确认成功");
        } else {
            toast("确认失败");
        }
    }

    @Override
    public void onCancelConfirmOrder(boolean isOk) {
        if (isOk) {
            OrdersActivity.action(mContext, 0);
            toast("确认取消成功");
            finish();
        } else {
            toast("确认取消失败");
        }
    }

    @Override
    public void onDistribution(boolean isOk) {
        if (isOk) {
            mPresenter.getOrderDetail(orderId);
            toast("分配成功");
        } else {
            toast("分配失败");
        }
    }

    @Override
    public void onCancelDistribution(boolean isOk) {
        if (isOk) {
            mPresenter.getOrderDetail(orderId);
            toast("取消分配成功");
        } else {
            toast("取消分配失败");
        }
    }

    @Override
    public void onCompleteDistribution(boolean isOk) {
        if (isOk) {
            mPresenter.getOrderDetail(orderId);
            toast("配送成功");
        } else {
            toast("配送失败");
        }
    }

    @Override
    public void onCancelReceive(boolean isOk) {
        if (isOk) {
            OrdersActivity.action(mContext, 0);
            toast("取消成功");
            finish();
        } else {
            toast("取消失败");
        }
    }

    @Override
    public void onCommentOrder(boolean isOk) {
        if (isOk) {
            mPresenter.getOrderDetail(orderId);
            toast("回执成功");
        } else {
            toast("回执失败");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.CODE_REQ) {
            if (data != null) {
                carId = data.getIntExtra("carId", 0);
                mPresenter.distribution(orderId, new OrderDistribution(0, carId));
            }
        }
    }

    public void showSelectDialog(LatLng start, LatLng end, String dName) {
        new CircleDialog.Builder(this)
                .configDialog(new ConfigDialog() {
                    @Override
                    public void onConfig(DialogParams params) {
                        params.animStyle = R.style.dialogWindowAnim;
                    }
                })
                .setItems(items, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) { //百度地图
                            MapUtils.startBaidu(mContext, start, end, dName);
                        } else {//高德地图
                            MapUtils.startGaode(mContext, start, end, dName);
                        }
                    }
                })
                .setNegative("取消", null)
                .show();
    }

    public void getPhoto(int choseCode) {
        this.choseCode = choseCode;
        showDialog();
    }

    private void showDialog() {
        new MaterialDialog.Builder(mContext)
                .items(item)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position == 0) {
                            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            Uri imageUri = Uri.fromFile(file);
                            getTakePhoto().onPickFromCapture(imageUri);
                        } else {
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

    @Override
    public void onUploadFile(String data) {
        switch (choseCode) {
            case 1:
                img1Url = data;
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, mImg1);
                break;
            case 2:
                img2Url = data;
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, mImg2);
                break;
            case 3:
                img3Url = data;
                CommonGlideImageLoader.getInstance().displayNetImage(mContext, com.js.http.global.Const.IMG_URL + data, mImg3);
                break;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(mContext, type, invokeParam, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFilePresenter != null) {
            mFilePresenter.detachView();
        }
    }

}
