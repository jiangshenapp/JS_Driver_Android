package com.js.driver.ui.center.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.js.driver.R;
import com.js.driver.model.bean.RouteBean;

import java.util.List;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/11
 * desc   :
 * version: 3.0.0
 */
public class RoutesAdapter extends BaseQuickAdapter<RouteBean, BaseViewHolder> {

    public RoutesAdapter(int layoutResId, @Nullable List<RouteBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RouteBean item) {
        helper.addOnClickListener(R.id.item_route_edit);
        helper.addOnClickListener(R.id.item_route_delete);
        helper.addOnClickListener(R.id.content);
        helper.setText(R.id.item_route_start,item.getStartAddressCodeName())
                .setText(R.id.item_route_end,item.getArriveAddressCodeName())
                .setText(R.id.item_route_desc,item.getCarLengthName()+"  "+item.getCarModelName());
        if (item.getClassic() == 1) {
            helper.getView(R.id.item_route_type).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.item_route_type).setVisibility(View.GONE);
        }
    }
}
