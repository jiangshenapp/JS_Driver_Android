package com.js.driver.ui.main.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerFragmentComponent;
import com.js.driver.di.module.FragmentModule;
import com.js.driver.global.Const;
import com.js.driver.model.bean.DictBean;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.event.CitySelectEvent;
import com.js.driver.model.event.SortEvent;
import com.js.driver.model.request.LineAppFind;
import com.js.driver.model.response.ListResponse;
import com.js.driver.presenter.DictPresenter;
import com.js.driver.presenter.contract.DictContract;
import com.js.driver.ui.main.adapter.FindOrderAdapter;
import com.js.driver.ui.main.presenter.FindOrderPresenter;
import com.js.driver.ui.main.presenter.contract.FindOrderContract;
import com.js.driver.ui.order.activity.OrderDetailActivity;
import com.js.driver.ui.order.activity.OrdersActivity;
import com.js.driver.widget.window.CityWindow;
import com.js.driver.widget.window.FilterWindow;
import com.js.driver.widget.window.SortWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xlgcx.frame.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/1.
 */
public class FindOrderFragment extends BaseFragment<FindOrderPresenter> implements FindOrderContract.View, BaseQuickAdapter.OnItemClickListener, DictContract.View {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.condition_layout)
    LinearLayout mCondition;
    @BindView(R.id.send_address)
    TextView mSendAddress;
    @BindView(R.id.end_address)
    TextView mEndAddress;
    @BindView(R.id.sort)
    TextView mSort;
    @BindView(R.id.filter)
    TextView mFilter;


    @OnClick({R.id.send_address, R.id.end_address, R.id.sort, R.id.filter,R.id.waybill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_address:
                mStartWindow.showAsDropDown(mCondition, 0, 0);
                break;
            case R.id.end_address:
                mEndWindow.showAsDropDown(mCondition, 0, 0);
                break;
            case R.id.sort:
                mSortWindow.showAsDropDown(mCondition, 0, 0);
                break;
            case R.id.filter:
                mFilterWindow.showAsDropDown(mCondition, 0, 0);
                break;
            case R.id.waybill:
                OrdersActivity.action(mContext, 0);
                break;
        }
    }


    private FindOrderAdapter mAdapter;
    private List<OrderBean> orders;
    private int type;
    private CityWindow mStartWindow;
    private CityWindow mEndWindow;
    private FilterWindow mFilterWindow;
    private SortWindow mSortWindow;
    private int sort;
    private LineAppFind lineAppFind = new LineAppFind();
    private String arriveAddressCode = "0";
    private String startAddressCode = "0";

    @Inject
    DictPresenter mDictPresenter;

    public static FindOrderFragment newInstance() {
        return new FindOrderFragment();
    }

    @Override
    protected void initInject() {
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .appComponent(App.getInstance().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_order;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    private void initData() {
        mDictPresenter.getDictByType(Const.DICT_CAR_TYPE_NAME);
        mDictPresenter.getDictByType(Const.DICT_LENGTH_NAME);
        mDictPresenter.getDictByType(Const.DICT_USE_CAR_TYPE_NAME);
    }



    private void initView() {
        mDictPresenter.attachView(this);
        initRecycler();
        initRefresh();
        initCityWindow();
    }

    private void initCityWindow() {
        mStartWindow = new CityWindow(mContext, 0);
        mEndWindow = new CityWindow(mContext, 1);
        mFilterWindow = new FilterWindow(mContext);
        mSortWindow = new SortWindow(mContext);
    }


    private void initRecycler() {
        mAdapter = new FindOrderAdapter(R.layout.item_home_order, orders);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.layout_data_empty,mRecycler);
        mAdapter.setOnItemClickListener(this);
    }

    private void initRefresh() {
        mRefresh.autoRefresh();
        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                type = Const.MORE;
                int num = (int) Math.ceil(((float)mAdapter.getItemCount() / Const.PAGE_SIZE)) + 1;
                getOrders(num);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                type = Const.REFRESH;
                getOrders(Const.PAGE_NUM);
            }
        });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<OrderBean> orderBeans = adapter.getData();
        OrderBean orderBean = orderBeans.get(position);
        if (orderBean != null) {
            OrderDetailActivity.action(mContext,orderBean.getId());
        }
    }

    @Override
    public void onFindOrders(ListResponse<OrderBean> response) {
        switch (type) {
            case Const.REFRESH:
                mAdapter.setNewData(response.getRecords());
                break;
            case Const.MORE:
                mAdapter.addData(response.getRecords());
                break;
        }
    }

    @Override
    public void finishRefreshAndLoadMore() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Subscribe
    public void onEvent(CitySelectEvent event) {
        switch (event.type) {
            case 0:
                startAddressCode = event.areaBean.getCode();
                if (!TextUtils.isEmpty(event.areaBean.getAlias())) {
                    mSendAddress.setText(event.areaBean.getAlias());
                } else {
                    mSendAddress.setText(event.areaBean.getName());
                }

                break;
            case 1:
                arriveAddressCode = event.areaBean.getCode();
                if (!TextUtils.isEmpty(event.areaBean.getAlias())) {
                    mEndAddress.setText(event.areaBean.getAlias());
                } else {
                    mEndAddress.setText(event.areaBean.getName());
                }
                break;
        }
        getOrders(Const.PAGE_NUM);
    }

    private void getOrders(int num){
        if ("发货地".equals(mSendAddress.getText().toString())) {
            lineAppFind.setStartAddressCode("0");
        } else {
            lineAppFind.setStartAddressCode(startAddressCode);
        }
        if ("收货地".equals(mEndAddress.getText().toString())) {
            lineAppFind.setArriveAddressCode("0");
        } else {
            lineAppFind.setArriveAddressCode(arriveAddressCode);
        }

        lineAppFind.setSort(sort);
        mPresenter.findOrders(num, (int)Const.PAGE_SIZE, lineAppFind);
    }

    @Subscribe
    public void onEvent(SortEvent sortEvent) {
        sort = sortEvent.type;
        mSort.setText(sort == 1 ? "默认排序" : "距离排序");
        getOrders(Const.PAGE_NUM);
    }

    @Override
    public void onDictByType(String type, List<DictBean> dictBeans) {
        switch (type) {
            case Const.DICT_CAR_TYPE_NAME:
                mFilterWindow.setTypes(dictBeans);
                break;
            case Const.DICT_LENGTH_NAME:
                mFilterWindow.setCarLengths(dictBeans);
                break;
            case Const.DICT_USE_CAR_TYPE_NAME:
                mFilterWindow.setCarTypes(dictBeans);
                break;
        }
    }
}
