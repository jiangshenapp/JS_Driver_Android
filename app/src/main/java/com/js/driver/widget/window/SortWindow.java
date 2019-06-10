package com.js.driver.widget.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


import com.js.driver.R;
import com.js.driver.model.bean.AreaBean;
import com.js.driver.model.bean.CityInfo;
import com.js.driver.model.event.SortEvent;
import com.js.driver.widget.window.adapter.CityAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyg on 2019-05-22.
 */
public class SortWindow extends PopupWindow {


    private Context mContext;
    private CityAdapter mAdapter;
    private List<AreaBean> mList;
    private CityInfo cityInfo = new CityInfo();
    private int level = 1;
    private String selectCode;
    private int type;

    public SortWindow(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_window_sort, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);//内容可点击
        setOutsideTouchable(false); //点击外部popupWindow消失
        setClippingEnabled(false);
        setBackgroundDrawable(null);
    }

    @OnClick({R.id.item_sort_default, R.id.item_sort_distance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_sort_default:
                EventBus.getDefault().post(new SortEvent(0));
                break;
            case R.id.item_sort_distance:
                EventBus.getDefault().post(new SortEvent(1));
                break;
        }
        dismiss();
    }
}
