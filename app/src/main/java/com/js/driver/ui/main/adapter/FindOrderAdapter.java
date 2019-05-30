package com.js.driver.ui.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.js.driver.R;
import com.js.driver.model.bean.OrderBean;

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
        helper.setText(R.id.item_order_no, item.getOrderNo())
                .setText(R.id.item_order_type, "")
                .setText(R.id.item_order_create_time, item.getCreateTime())
                .setText(R.id.item_send_address, item.getSendAddress())
                .setText(R.id.item_receive_address, item.getReceiveAddress())
                .setText(R.id.item_loading_time, item.getLoadingTime())
                .setText(R.id.item_good_info, item.getGoodsType() +
                        "/" +
                        item.getGoodsVolume() +
                        "/" +
                        item.getGoodsWeight());
        helper.setText(R.id.item_order_money, "￥" + item.getFee());
    }
}
