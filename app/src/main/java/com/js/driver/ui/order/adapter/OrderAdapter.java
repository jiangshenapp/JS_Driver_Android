package com.js.driver.ui.order.adapter;

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
        helper.setText(R.id.item_waybill_order_number, "订单编号：" + item.getOrderNo())
                .setText(R.id.item_waybill_send_address, item.getSendAddress())
                .setText(R.id.item_waybill_arrive_address, item.getReceiveAddress())
                .setText(R.id.item_waybill_money, "￥" + item.getFee())
                .setText(R.id.item_waybill_order_status, item.getStateName())
                .setText(R.id.item_waybill_car_info, item.getGoodsVolume() + "方/"
                        + item.getGoodsWeight() + "吨");
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
