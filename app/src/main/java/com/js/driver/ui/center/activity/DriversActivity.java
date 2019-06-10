package com.js.driver.ui.center.activity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.di.componet.DaggerActivityComponent;
import com.js.driver.di.module.ActivityModule;
import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.event.AddDriverEvent;
import com.js.driver.model.response.ListResponse;
import com.js.driver.ui.center.adapter.DriversAdapter;
import com.js.driver.ui.center.presenter.DriversPresenter;
import com.js.driver.ui.center.presenter.contract.DriversContract;
import com.js.driver.widget.dialog.AddDriverFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xlgcx.frame.view.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by huyg on 2019/4/29.
 */
public class DriversActivity extends BaseActivity<DriversPresenter> implements DriversContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    private DriversAdapter mAdapter;
    private List<DriverBean> mDrivers;


    public static void action(Context context){
        Intent intent = new Intent(context,DriversActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    private void initData() {
        mPresenter.getDriverList();
    }

    private void initView() {
        initRecycler();
        initRefresh();
    }

    private void initRefresh() {
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getDriverList();
            }
        });
    }

    private void initRecycler() {
        mAdapter = new DriversAdapter(R.layout.item_mine_driver, mDrivers);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
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
        return R.layout.activity_center_drivers;
    }

    @Override
    public void setActionBar() {
        mTitle.setText("我的司机");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_center_driver, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_driver:
                showDialog();
                break;
        }
        return true;
    }

    private void showDialog() {
        AddDriverFragment driverFragment = new AddDriverFragment();
        driverFragment.show(getSupportFragmentManager(),"Add");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        List<DriverBean> driverBean = adapter.getData();
        switch (view.getId()){
            case R.id.item_driver_delete:

                break;
        }
    }

    @Subscribe
    public void onEvent(AddDriverEvent event){

    }

    @Override
    public void onDriverList(ListResponse<DriverBean> response) {
        mDrivers = response.getRecords();
        mAdapter.setNewData(mDrivers);
    }

    @Override
    public void finishRefreshAndLoadMore() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }
}
