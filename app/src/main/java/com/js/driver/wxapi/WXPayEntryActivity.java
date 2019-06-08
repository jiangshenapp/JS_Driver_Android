package com.js.driver.wxapi;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.js.driver.R;
import com.js.driver.global.Const;
import com.js.driver.model.bean.WxPayBean;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xlgcx.frame.view.SimpleActivity;

/**
 * Created by huyg on 2019-05-31.
 */
public class WXPayEntryActivity extends SimpleActivity implements IWXAPIEventHandler {


    private IWXAPI api;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.d(getClass().getSimpleName(), baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d(getClass().getSimpleName(), "onPayFinish, errCode = " + baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.d(getClass().getSimpleName(), String.valueOf(baseResp.errStr));
            Log.d(getClass().getSimpleName(), String.valueOf(baseResp.errCode));
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wx_pay;
    }

    @Override
    protected void init() {
        api = WXAPIFactory.createWXAPI(this, Const.APP_ID);
        api.handleIntent(getIntent(), this);
        initIntent();
    }

    private void initIntent() {
        String orderInfo = getIntent().getStringExtra("orderInfo");
        WxPayBean wxPayBean = new Gson().fromJson(orderInfo, WxPayBean.class);
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (!isPaySupported) {
            Toast.makeText(mContext, "未安装微信，不能使用微信支付", Toast.LENGTH_SHORT).show();
            return;
        }
        PayReq request = new PayReq();
        request.appId = wxPayBean.getAppid();
        request.partnerId = wxPayBean.getPartnerid();
        request.prepayId = wxPayBean.getPrepayid();
        request.packageValue = wxPayBean.getPackageX();
        request.nonceStr = wxPayBean.getNoncestr();
        request.timeStamp = wxPayBean.getTimestamp();
        request.sign = wxPayBean.getSign();
        boolean status = api.sendReq(request);
        Toast.makeText(mContext, ""+status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setActionBar() {
        mToolbar.setVisibility(View.GONE);
    }


}
