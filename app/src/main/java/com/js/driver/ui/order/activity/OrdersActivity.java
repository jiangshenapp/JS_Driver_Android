package com.js.driver.ui.order.activity;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.tabs.TabLayout;
import com.js.driver.R;
import com.js.driver.ui.order.fragment.OrderFragment;
import com.xlgcx.frame.view.SimpleActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Created by huyg on 2019/4/29.
 */
public class OrdersActivity extends SimpleActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    public static void action(Context context,int type){
        Intent intent = new Intent(context,OrdersActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    //2待接单，3待确认，4待货主付款，5待接货, 6待送达，7待货主评价，8已完成，9已取消，10已关闭）
    private String[] titles = {"全部", "待接单", "待确认","待接货","待送达"};
    private int type;//0:全部,1待接单,2待确认,3待接货,4待送达
    private List<Fragment> mFragments;


    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected void init() {
        initIntent();
        initView();
    }


    private void initIntent() {
        type = getIntent().getIntExtra("type", 0);
    }

    private void initView() {
        initFragment();
        initViewPager();
        mViewpager.setCurrentItem(type);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(OrderFragment.newInstance(0));
        mFragments.add(OrderFragment.newInstance(2));
        mFragments.add(OrderFragment.newInstance(3));
        mFragments.add(OrderFragment.newInstance(5));
        mFragments.add(OrderFragment.newInstance(6));
    }

    private void initViewPager() {
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void setActionBar() {
        mTitle.setText("我的运单");
    }

}
