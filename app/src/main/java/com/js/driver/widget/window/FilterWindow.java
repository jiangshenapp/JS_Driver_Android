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
import com.js.driver.model.event.CitySelectEvent;
import com.js.driver.widget.window.adapter.CityAdapter;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyg on 2019-05-22.
 */
public class FilterWindow extends PopupWindow implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.window_city)
    TextView mCity;
    @BindView(R.id.window_upper)
    TextView mUpper;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;


    private Context mContext;
    private CityAdapter mAdapter;
    private List<AreaBean> mList;
    private CityInfo cityInfo = new CityInfo();
    private int level = 1;
    private String selectCode;
    private int type;

    public FilterWindow(Context context, int type) {
        super(context);
        this.mContext = context;
        this.type = type;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_window_filter, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);//内容可点击
        setOutsideTouchable(false); //点击外部popupWindow消失
        setClippingEnabled(false);
        setBackgroundDrawable(null);
        initView();
        initData();
    }

    private void initData() {
        String cityStr = getJson(mContext, "area.json");
        ChinaArea chinaArea = new Gson().fromJson(cityStr, ChinaArea.class);
        AreaBean areaBean = chinaArea.getChina();
        AreaBean china = new AreaBean("全国", "0000", true, "全国");
        areaBean.getChild().add(0, china);
        cityInfo.setProvince(areaBean.getChild());
        mAdapter.setNewData(areaBean.getChild());
    }

    private void initView() {
        initRecycler();
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
        mAdapter = new CityAdapter(R.layout.item_window_city, mList);
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

    }

    @OnClick({R.id.window_upper, R.id.blank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.window_upper:
                switch (level) {
                    case 2:
                        level = 1;
                        mAdapter.setNewData(cityInfo.getProvince());
                        mUpper.setVisibility(View.GONE);
                        break;
                    case 3:
                        level = 2;
                        mAdapter.setNewData(cityInfo.getCity());
                        mUpper.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case R.id.blank:
                dismiss();
                break;
        }
    }


    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<AreaBean> areaBeans = mAdapter.getData();
        AreaBean selectArea = areaBeans.get(position);
        for (int i = 0; i < areaBeans.size(); i++) {
            if (i == position) {
                areaBeans.get(i).setChecked(true);
            } else {
                areaBeans.get(i).setChecked(false);
            }
        }
        mAdapter.setNewData(areaBeans);
        if (!TextUtils.isEmpty(selectArea.getAlias())) {
            selectCode = selectArea.getCode();
            mUpper.setVisibility(View.VISIBLE);
            mCity.setText("选择:" + selectArea.getName());
            dismiss();
            EventBus.getDefault().post(new CitySelectEvent(type, selectArea));
            return;
        }

        //
        AreaBean areaBean = new AreaBean();
        try {
            areaBean = (AreaBean) selectArea.clone();
            areaBean.setChild(null);
            switch (level) {
                case 1:
                    areaBean.setAlias(areaBean.getName());
                    areaBean.setName("全省");
                    break;
                case 2:
                    areaBean.setAlias(areaBean.getName());
                    areaBean.setName("全市");
                    break;
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if (selectArea.getChild() != null && selectArea.getChild().size() > 0) {
            if (TextUtils.isEmpty(selectArea.getChild().get(0).getAlias())) {
                selectArea.getChild().add(0, areaBean);
            }
            mAdapter.setNewData(selectArea.getChild());
        } else {
            EventBus.getDefault().post(new CitySelectEvent(type, selectArea));
            dismiss();
        }
        switch (level) {
            case 1:
                level = 2;
                cityInfo.setCity(selectArea.getChild());
                break;
            case 2:
                level = 3;
                cityInfo.setDistrict(selectArea.getChild());
                break;
        }
        selectCode = selectArea.getCode();
        mUpper.setVisibility(View.VISIBLE);
        mCity.setText("选择:" + selectArea.getName());
    }
}
