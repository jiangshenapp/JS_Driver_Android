package com.js.driver.ui.wallet.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.js.driver.R;
import com.js.driver.global.Const;
import com.js.driver.model.bean.PayRouter;

import java.util.List;

/**
 * Created by huyg on 2019-05-31.
 */
public class PayAdapter extends BaseQuickAdapter<PayRouter, BaseViewHolder> {

    public PayAdapter(int layoutResId, @Nullable List<PayRouter> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayRouter item) {
        switch (item.getChannelType()) {
            case Const.CHANNEL_ALI_PAY:
                helper.setImageResource(R.id.item_pay_type_img,R.mipmap.ic_wallet_payment_alipay);
                helper.setText(R.id.item_pay_type_name,"支付宝");
                helper.setChecked(R.id.item_pay_type_check,item.isChecked());
                break;
            case Const.CHANNEL_WX_PAY:
                helper.setImageResource(R.id.item_pay_type_img,R.mipmap.ic_wallet_payment_weixin);
                helper.setText(R.id.item_pay_type_name,"微信");
                helper.setChecked(R.id.item_pay_type_check,item.isChecked());
                break;
            case Const.CHANNEL_UNION_PAY:
//                helper.setImageResource(R.id.item_pay_type_img,R.mipmap.ic_wallet_payment_balance);
//                helper.setText(R.id.item_pay_type_name,"微信");
                break;
        }
    }
}
