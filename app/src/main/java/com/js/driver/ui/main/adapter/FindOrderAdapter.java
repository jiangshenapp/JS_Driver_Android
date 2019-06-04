package com.js.driver.ui.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.js.driver.R;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.util.TimeUtils;

import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by huyg on 2019/4/24.
 */
public class FindOrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    public FindOrderAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        helper.setText(R.id.item_order_no, "订单编号"+item.getOrderNo())
                .setText(R.id.item_order_type, item.getUseCarTypeName())
                .setText(R.id.item_send_address, item.getSendAddress())
                .setText(R.id.item_receive_address, item.getReceiveAddress())
                .setText(R.id.item_loading_time, "装货时间"+item.getLoadingTime())
                .setText(R.id.item_good_info, item.getGoodsType() +
                        "/" +
                        item.getGoodsVolume() +
                        "/" +
                        item.getGoodsWeight());
        helper.setText(R.id.item_order_money, "￥" + item.getFee());
        helper.setText(R.id.item_order_create_time, TimeUtils.format(new Date(item.getCreateTime()))+"发布");
    }
}
