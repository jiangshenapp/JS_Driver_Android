package com.js.driver.ui.main.fragment;

import com.js.driver.App;
import com.js.driver.di.module.FragmentModule;
import com.js.driver.ui.main.presenter.CommunityPresenter;
import com.js.driver.ui.main.presenter.contract.CommunityContract;
import com.xlgcx.frame.view.BaseFragment;

/**
 * Created by huyg on 2019/4/1.
 */
public class CommunityFragment extends BaseFragment<CommunityPresenter> implements CommunityContract.View{

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }


    @Override
    protected void initInject() {
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .appComponent(App.getInstance())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void init() {

    }
}
