package com.js.driver.widget.window;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.js.driver.R;
import com.js.driver.model.bean.AreaBean;
import com.js.driver.model.bean.ChinaArea;
import com.js.driver.model.bean.CityInfo;
import com.js.driver.model.bean.DictBean;
import com.js.driver.model.event.CitySelectEvent;
import com.js.driver.model.event.DictSelectEvent;
import com.js.driver.widget.window.adapter.CityAdapter;
import com.js.driver.widget.window.adapter.DictAdapter;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyg on 2019-05-22.
 */
public class ItemWindow extends PopupWindow implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.window_title)
    TextView mTitle;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;


    @OnClick({R.id.cancel, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                break;
            case R.id.submit:
                List<DictBean> dictBeans = mAdapter.getData();
                StringBuilder valueStr = new StringBuilder();
                StringBuilder labelStr = new StringBuilder();
                for (DictBean dictBean : dictBeans) {
                    if (dictBean.isChecked()) {
                        if (!"不限".equals(dictBean.getValue())) {
                            valueStr.append(dictBean.getValue());
                            valueStr.append(",");
                            labelStr.append(dictBean.getLabel());
                            labelStr.append(",");
                        }else{
                            labelStr.append(dictBean.getLabel());
                            labelStr.append(",");
                        }
                    }
                }
                if (labelStr.length() > 0) {
                    labelStr.deleteCharAt(labelStr.length() - 1);
                }

                if (valueStr.length() > 0) {
                    valueStr.deleteCharAt(valueStr.length() - 1);
                }

                EventBus.getDefault().post(new DictSelectEvent(labelStr.toString(), valueStr.toString(), type));
        }
        dismiss();
    }

    private Context mContext;
    private DictAdapter mAdapter;
    private List<DictBean> mDictBeans;
    private int type;

    public ItemWindow(Context context, int type) {
        super(context);
        this.mContext = context;
        this.type = type;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_window_item, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);//内容可点击
        setOutsideTouchable(true); //点击外部popupWindow消失
        setBackgroundDrawable(null);
        initView();
    }

    private void initView() {
        initRecycler();
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
        mAdapter = new DictAdapter(R.layout.item_window_dict, mDictBeans);
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<DictBean> dictBeans = mAdapter.getData();
        DictBean dictBean = dictBeans.get(position);
        if ("不限".equals(dictBean.getValue())) {
            for (DictBean dictBean1 : dictBeans) {
                dictBean1.setChecked(false);
            }
            dictBean.setChecked(true);
        } else {
            dictBeans.get(0).setChecked(false);
            dictBean.setChecked(!dictBean.isChecked());
        }
        mAdapter.setNewData(dictBeans);
    }


    public void setData(List<DictBean> data) {
        this.mDictBeans = data;
        this.mDictBeans.add(0, new DictBean("不限", "不限", true));
        mAdapter.setNewData(mDictBeans);
    }
}