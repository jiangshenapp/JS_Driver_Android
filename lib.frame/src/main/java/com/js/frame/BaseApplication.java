package com.xlgcx.frame;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.tencent.bugly.crashreport.CrashReport;
import com.xlgcx.frame.global.Const;

import androidx.multidex.MultiDexApplication;

/**
 * Created by huyg on 2018/8/22.
 */
public class BaseApplication extends MultiDexApplication {


    static {
        //初始化上拉刷新及上拉加载
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((Context context, RefreshLayout layout) -> new MaterialHeader(context));
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((Context context, RefreshLayout layout) -> new ClassicsFooter(context));
    }



    private static BaseApplication instance;
    public static synchronized BaseApplication getInstance() {
        return instance;
    }
    private ApplicationDelegate mApplicationDelegate;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mApplicationDelegate = new ApplicationDelegate(base);
        mApplicationDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationDelegate.onCreate(this);
        instance = this;
        initCrash();
        initARouter();
    }




    @Override
    public void onTerminate() {
        super.onTerminate();
        mApplicationDelegate.onTerminate(this);
    }


    private void initCrash() {
        if (BuildConfig.DEBUG) {
            CrashReport.initCrashReport(getApplicationContext(), Const.BUGLY_APP_ID, true);
        } else {
            CrashReport.initCrashReport(getApplicationContext(), Const.BUGLY_APP_ID, false);
        }
    }


    private void initARouter() {
        if (BuildConfig.DEBUG){
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }


}
