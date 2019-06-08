package com.js.driver.ui.wallet.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.model.bean.BillBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by huyg on 2019/4/24.
 */
public class BillAdapter extends BaseQuickAdapter<BillBean, BaseViewHolder> {

    public BillAdapter(int layoutResId, @Nullable List<BillBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillBean item) {
        helper.setText(R.id.item_bill_id,"订单编号："+item.getTradeNo())
                .setText(R.id.item_bill_type,item.getRemark())
                .setText(R.id.item_bill_time,item.getCreateTime())
                .setText(R.id.item_bill_price,String.valueOf(item.getTradeMoney()));

        if (item.getTradeMoney()<0) {
            helper.setTextColor(R.id.item_bill_price, App.getInstance().getResources().getColor(R.color._D0021B));
        } else {
            helper.setTextColor(R.id.item_bill_price, App.getInstance().getResources().getColor(R.color._69BC0D));
        }
    }
}
