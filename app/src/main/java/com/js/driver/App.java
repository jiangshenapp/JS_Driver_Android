package com.js.driver;

import com.baidu.location.BDLocation;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.js.driver.di.componet.AppComponent;
import com.js.driver.di.componet.DaggerAppComponent;
import com.js.driver.di.module.AppModule;
import com.js.driver.manager.SpManager;
import com.js.driver.model.bean.UserInfo;
import com.xlgcx.frame.BaseApplication;
import com.xlgcx.http.HttpApp;

/**
 * Created by huyg on 2019/4/1.
 */
public class App extends BaseApplication {

    private AppComponent mAppComponent;
    private static App mApp;
    public String token;
    public Gson mGson = new Gson();
    public String avatar;
    public String mobile;
    public String nickName;
    public int driverVerified;
    public int parkVerified;
    public int companyConsignorVerified;
    public int personConsignorVerified;
    public BDLocation mLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Stetho.initializeWithDefaults(this);
        initDaggerComponent();
        getUserInfo();
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        this.token = SpManager.getInstance(this).getSP("token");
        this.avatar = SpManager.getInstance(this).getSP("avatar");
        this.mobile = SpManager.getInstance(this).getSP("mobile");
        this.nickName = SpManager.getInstance(this).getSP("nickName");
        this.driverVerified = SpManager.getInstance(this).getIntSP("driverVerified");
        this.parkVerified = SpManager.getInstance(this).getIntSP("parkVerified");
        this.companyConsignorVerified = SpManager.getInstance(this).getIntSP("companyConsignorVerified");
        this.personConsignorVerified = SpManager.getInstance(this).getIntSP("personConsignorVerified");

        HttpApp.getApp().token = this.token;
    }

    /**
     * 存储用户信息
     */
    public void putUserInfo(UserInfo userInfo) {
        SpManager.getInstance(this).putSP("avatar",userInfo.getAvatar());
        SpManager.getInstance(this).putSP("mobile",userInfo.getMobile());
        SpManager.getInstance(this).putSP("nickName",userInfo.getNickName());
        SpManager.getInstance(this).putIntSP("driverVerified",userInfo.getDriverVerified());
        SpManager.getInstance(this).putIntSP("parkVerified",userInfo.getParkVerified());
        SpManager.getInstance(this).putIntSP("companyConsignorVerified",userInfo.getCompanyConsignorVerified());
        SpManager.getInstance(this).putIntSP("personConsignorVerified",userInfo.getPersonConsignorVerified());

        getUserInfo();
    }

    /**
     * 存储token
     */
    public void putToken(String token) {
        HttpApp.getApp().token = token;
        SpManager.getInstance(this).putSP("token",token);
        this.token = token;
    }

    /**
     * 清空用户信息
     */
    public void clearUserInfo() {
        SpManager.getInstance(App.getInstance()).clear();
        getUserInfo();
    }

    /**
     * 获取连接器
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * 初始化Dagger所使用的连接器
     */
    private void initDaggerComponent() {
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule())
                .build();
    }

    public static App getInstance() {
        return mApp;
    }

    public String gsonFormat(Object clazz) {
        return mGson.toJson(clazz);
    }

}
