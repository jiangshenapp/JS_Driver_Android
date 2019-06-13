package com.js.driver.ui.center.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.model.bean.RouteBean;
import com.js.driver.model.response.ListResponse;
import com.js.driver.ui.center.adapter.RoutesAdapter;
import com.js.driver.ui.center.presenter.RoutesPresenter;
import com.js.driver.ui.center.presenter.contract.RoutesContract;
import com.js.driver.widget.adapter.Divider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xlgcx.frame.view.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/11
 * desc   : 我的路线
 * version: 3.0.0
 */
public class RoutesActivity extends BaseActivity<RoutesPresenter> implements RoutesContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    private RoutesAdapter mAdapter;
    private List<RouteBean> mRoutes;

    public static void action(Context context) {
        Intent intent = new Intent(context, RoutesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void init() {
        initView();
    }

    private void initData() {
        mPresenter.getRouteList();
    }

    private void initView() {
        initRecycler();
        initRefresh();
    }

    private void initRefresh() {
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getRouteList();
            }
        });
    }

    private void initRecycler() {
        mAdapter = new RoutesAdapter(R.layout.item_mine_route, mRoutes);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.addItemDecoration(new Divider(getResources().getDrawable(R.drawable.divider_lines), LinearLayoutManager.VERTICAL));
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void initInject() {
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(App.getInstance().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_route;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("我的路线");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_center_route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_route:
                AddRouteActivity.action(mContext,1);
                break;
        }
        return true;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        List<RouteBean> routeBeans = adapter.getData();
        RouteBean routeBean = routeBeans.get(position);

        switch (view.getId()) {
            case R.id.item_route_edit:
                AddRouteActivity.action(mContext,2,routeBean);
                break;
            case R.id.item_route_delete:
                mPresenter.removeRoute(routeBean.getId());
                break;
            case R.id.content:
                RoutesDetailActivity.action(mContext, routeBean.getId());
                break;
        }
    }

    @Override
    public void onRouteList(ListResponse<RouteBean> response) {
        mRoutes = response.getRecords();
        mAdapter.setNewData(mRoutes);
    }

    @Override
    public void finishRefreshAndLoadMore() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public void onRemoveRoute() {
        toast("路线删除成功");
        mPresenter.getRouteList();
    }
}
