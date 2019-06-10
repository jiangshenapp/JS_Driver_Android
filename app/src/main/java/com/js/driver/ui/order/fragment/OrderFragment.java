package com.js.driver.ui.order.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerFragmentComponent;
import com.js.driver.di.module.FragmentModule;
import com.js.driver.global.Const;
import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.response.ListResponse;
import com.js.driver.ui.order.activity.OrderDetailActivity;
import com.js.driver.ui.order.adapter.OrderAdapter;
import com.js.driver.ui.order.presenter.OrderPresenter;
import com.js.driver.ui.order.presenter.contract.OrderContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xlgcx.frame.view.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrderFragment extends BaseFragment<OrderPresenter> implements OrderContract.View, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    private OrderAdapter mAdapter;
    private List<OrderBean> mOrders;
    private int type;

    public static OrderFragment newInstance(int type) {
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        orderFragment.setArguments(bundle);
        return orderFragment;
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
        return R.layout.fragment_order;
    }

    @Override
    protected void init() {
        initView();
    }


    private void initView() {
        initRecycler();
        initRefresh();
    }

    private void initRefresh() {
        mRefresh.autoRefresh();
        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                type = Const.MORE;
                int num = (int) Math.ceil(((float)mAdapter.getItemCount() / Const.PAGE_SIZE)) + 1;
                mPresenter.getOrderList(num, Const.PAGE_SIZE, type);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                type = Const.REFRESH;
                mPresenter.getOrderList(Const.PAGE_NUM, Const.PAGE_SIZE, type);
            }
        });
    }

    private void initRecycler() {
        mAdapter = new OrderAdapter(R.layout.item_waybill, mOrders);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<OrderBean> orderBeans = adapter.getData();
        OrderBean orderBean = orderBeans.get(position);
        if (orderBean != null) {
            OrderDetailActivity.action(mContext, orderBean.getId());
        }
    }


    @Override
    public void onOrderList(ListResponse<OrderBean> response) {
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
}
