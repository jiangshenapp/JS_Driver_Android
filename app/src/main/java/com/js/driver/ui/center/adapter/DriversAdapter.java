package com.js.driver.ui.center.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.js.driver.R;
import com.js.driver.manager.CommonGlideImageLoader;
import com.js.driver.model.bean.DriverBean;
import com.js.http.global.Const;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by huyg on 2019/4/29.
 */
public class DriversAdapter extends BaseQuickAdapter<DriverBean, BaseViewHolder> {

    public DriversAdapter(int layoutResId, @Nullable List<DriverBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DriverBean item) {
        helper.addOnClickListener(R.id.item_driver_delete);
        helper.addOnClickListener(R.id.content);
        helper.setText(R.id.item_driver_name,item.getDriverName())
                .setText(R.id.item_driver_phone,item.getDriverPhone())
                .setText(R.id.item_driver_type,"驾照类型："+item.getDriverLevel());
        CommonGlideImageLoader.getInstance().displayNetImageWithCircle(mContext, Const.IMG_URL + item.getAvatar()
                , helper.getView(R.id.item_driver_avatar), mContext.getResources().getDrawable(R.mipmap.ic_center_driver_head_land));
    }
}
