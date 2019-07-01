package com.js.driver.ui.wallet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.global.Const;
import com.js.driver.model.bean.PayInfo;
import com.js.driver.model.bean.PayRouter;
import com.js.driver.ui.wallet.adapter.PayAdapter;
import com.js.driver.ui.wallet.presenter.RechargePresenter;
import com.js.driver.ui.wallet.presenter.contract.RechargeContract;
import com.js.driver.util.AppUtils;
import com.js.driver.util.pay.PayResult;
import com.js.driver.widget.adapter.Divider;
import com.js.driver.wxapi.WXPayEntryActivity;
import com.js.frame.view.BaseActivity;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/24.
 */
public class RechargeActivity extends BaseActivity<RechargePresenter> implements RechargeContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recharge_money)
    EditText mMoney;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private static final int SDK_PAY_FLAG = 99;
    private int type;
    private PayAdapter mAdapter;
    private List<PayRouter> mPayRouters;
    private int channelType = 0;
    private int routerId = 0;

    public static void action(Context context) {
        context.startActivity(new Intent(context, RechargeActivity.class));
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    private void initView() {
        mMoney.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new PayAdapter(R.layout.item_pay_type, mPayRouters);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.addItemDecoration(new Divider(getResources().getDrawable(R.drawable.divider_center_cars), LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(this);
    }

    private void initData() {
        mPresenter.getPayRouter();
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
        return R.layout.activity_wallet_recharge;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("充值");
    }

    @OnClick({R.id.wallet_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wallet_recharge://充值
                String money = mMoney.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    toast("请输入金额");
                    return;
                }

                if (!AppUtils.isMoney(money)) {
                    toast("金钱输入有误");
                    return;
                }
                //交易类型, 1账户充值, 5运费支付，10运力端保证金，11货主端保证金
                mPresenter.payOrder(1, channelType, Double.parseDouble(money), routerId);
                break;
        }
    }

    public void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝支付业务：入参app_id
     */
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();

                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    public void onPayOrder(PayInfo payInfo) {
        switch (channelType) {
            case Const.CHANNEL_ALI_PAY:
                aliPay(payInfo.getOrderInfo());
                break;
            case Const.CHANNEL_WX_PAY:
                wxPay(payInfo.getOrderInfo());
                break;
        }
    }

    private void wxPay(String orderInfo) {
        if (TextUtils.isEmpty(orderInfo)) {
            toast("获取支付信息失败");
            return;
        }
        Intent intent = new Intent(mContext, WXPayEntryActivity.class);
        intent.putExtra("orderInfo",orderInfo);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 888) { //微信支付成功
            finish();
        }
    }

    @Override
    public void onPayRouter(List<PayRouter> payRouters) {
        if (payRouters != null && payRouters.size() > 0) {
            channelType = payRouters.get(0).getChannelType();
            routerId = payRouters.get(0).getRouteId();
            payRouters.get(0).setChecked(true);
            mAdapter.setNewData(payRouters);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<PayRouter> payRouters = adapter.getData();
        channelType = payRouters.get(position).getChannelType();
        routerId = payRouters.get(position).getRouteId();
        for (int i = 0; i < payRouters.size(); i++) {
            if (i == position) {
                payRouters.get(position).setChecked(true);
            } else {
                payRouters.get(i).setChecked(false);
            }
        }
        mAdapter.setNewData(payRouters);
    }
}
