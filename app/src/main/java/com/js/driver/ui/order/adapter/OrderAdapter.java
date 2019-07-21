package com.js.driver.ui.order.adapter;

import android.text.TextUtils;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.model.bean.OrderBean;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    private DecimalFormat df = new DecimalFormat("#####0.0");

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
        if (App.getInstance().mLocation == null) {
            return;
        }
        try {
            double distance = DistanceUtil.getDistance(new Gson().fromJson(item.getSendPosition(), LatLng.class), new LatLng(App.getInstance().mLocation.getLatitude(), App.getInstance().mLocation.getLongitude()));
            helper.setText(R.id.item_waybill_distance, "距离您" + (distance > 1000 ? df.format(distance / 1000) + " Km" : ((int) distance) + "米"));
        }catch (Exception e){

        }
    }
}
