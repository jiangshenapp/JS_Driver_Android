package com.js.driver.ui.order.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.js.driver.R;
import com.js.driver.model.bean.OrderBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {


    public OrderAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        String info = "";
        if (!TextUtils.isEmpty(item.getCarModelName())) {
            info += item.getCarModelName();
        }
        if (!TextUtils.isEmpty(item.getCarLengthName())) {
            info += item.getCarLengthName();
        }
        if (item.getGoodsVolume()!=0) {
            info += "/"+item.getGoodsVolume()+"方";
        }
        if (item.getGoodsWeight()!=0) {
            info += "/"+item.getGoodsWeight()+"吨";
        }
        helper.setText(R.id.item_waybill_order_number, "订单编号：" + item.getOrderNo())
                .setText(R.id.item_waybill_send_address, item.getSendAddress())
                .setText(R.id.item_waybill_arrive_address, item.getReceiveAddress())
                .setText(R.id.item_waybill_money, "￥" + item.getFee())
                .setText(R.id.item_waybill_order_status, item.getStateName())
                .setText(R.id.item_waybill_car_info, info);
        switch (item.getFeeType()) {
            case 1:
                helper.setText(R.id.item_waybill_money, "￥" + item.getFee());
                break;
            case 2:
                helper.setText(R.id.item_waybill_money, "电议");
                break;
        }

    }
}
