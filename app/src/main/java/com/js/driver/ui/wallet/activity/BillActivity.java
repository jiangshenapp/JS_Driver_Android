package com.js.driver.ui.wallet.activity;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.tabs.TabLayout;
import com.js.driver.R;
import com.js.driver.ui.wallet.fragment.BillFragment;
import com.js.frame.view.SimpleActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Created by huyg on 2019/4/24.
 */
public class BillActivity extends SimpleActivity {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private List<Fragment> mFragments;
    private String[] titles = {"全部", "余额", "保证金"};
    private int type;//0:全部,1余额,2保证金

    public static void action(Context context, int type) {
        Intent intent = new Intent(context, BillActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet_bill;
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
        mFragments.add(BillFragment.newInstance(0));
        mFragments.add(BillFragment.newInstance(1));
        mFragments.add(BillFragment.newInstance(2));
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
        mTitle.setText("账单明细");
    }

}
