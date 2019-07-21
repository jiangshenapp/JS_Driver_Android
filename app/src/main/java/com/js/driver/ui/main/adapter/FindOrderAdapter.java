package com.js.driver.ui.main.adapter;

import android.text.TextUtils;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.util.TimeUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by huyg on 2019/4/24.
 */
public class FindOrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    private DecimalFormat df = new DecimalFormat("#####0.0");

    public FindOrderAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

        String info = "";
        if (!TextUtils.isEmpty(item.getGoodsType())) {
            info += item.getGoodsType();
        }
        info += " " + item.getGoodsVolume() + "方";
        info += "/" + item.getGoodsWeight() + "吨";

        helper.setText(R.id.item_order_no, "订单编号：" + item.getOrderNo())
                .setText(R.id.item_order_type, item.getUseCarTypeName())
                .setText(R.id.item_send_address, item.getSendAddress())
                .setText(R.id.item_receive_address, item.getReceiveAddress())
                .setText(R.id.item_loading_time, "装货时间：" + item.getLoadingTime())
                .setText(R.id.item_good_info, info);
        if (item.getFeeType() == 1) {
            helper.setText(R.id.item_order_money, "￥" + item.getFee());
        } else {
            helper.setText(R.id.item_order_money, "电议");
        }
        helper.setText(R.id.item_order_create_time, TimeUtils.format(item.getCreateTime()) + "发布");
        if (App.getInstance().mLocation == null) {
            return;
        }
        try {
            double distance = DistanceUtil.getDistance(new Gson().fromJson(item.getSendPosition(), LatLng.class), new LatLng(App.getInstance().mLocation.getLatitude(), App.getInstance().mLocation.getLongitude()));
            helper.setText(R.id.item_distance, "距离您" + (distance > 1000 ? df.format(distance / 1000) + " Km" : ((int) distance) + "米"));
        }catch (Exception e){

        }

    }
}
