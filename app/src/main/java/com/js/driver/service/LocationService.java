package com.js.driver.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.js.driver.App;


/**
 * Create by Weixf
 * Date on 2017/7/24
 * Description 定位服务
 */
public class LocationService extends Service {
    private static final String TAG = "LocationService";
    private LocationClient locationClient;
    private MyLocationListener listener = new MyLocationListener();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        locationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
        locationClient.registerLocationListener(listener); // 注册监听函数
        initOption();
        startLocation();
    }

    private void initOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
        option.setOpenAutoNotifyMode();
        locationClient.setLocOption(option);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Notification.Builder builder = new Notification.Builder(this);
//        Intent nfIntent = new Intent(this, MainActivity.class);
//        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
//                .setContentTitle("正在进行后台定位")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentText("后台定位通知")
//                .setAutoCancel(true)
//                .setWhen(System.currentTimeMillis());
//        Notification notification = null;
//        notification = builder.build();
//        locationClient.enableLocInForeground(1001, notification);// 调起前台定位
        return super.onStartCommand(intent, flags, startId);
    }

    public void startLocation() {
        if (locationClient != null) {
            locationClient.start();
        }
    }

    public void restartLocation() {
        if (locationClient != null) {
            locationClient.restart();
        }
    }

    public void stopLocation() {
        if (locationClient != null) {
            locationClient.stop();
            locationClient.disableLocInForeground(true);
        }
    }

    public void startOfflineLocation() {
        if (locationClient != null) {
            locationClient.requestOfflineLocation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocation();
        stopSelf();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        locationClient.stop();
        return super.onUnbind(intent);
    }


    public class MyBinder extends Binder {

        public LocationService getService() {
            return LocationService.this;
        }
    }

    private MyBinder myBinder = new MyBinder();


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location != null) {
                Log.d(TAG, "getLocType--->" + location.getLocType());
                switch (location.getLocType()) {
                    case 66:
                    case 161:
                    case 61:
                        Log.d(TAG, "longitude--->" + location.getLongitude());
                        Log.d(TAG, "longitude--->" + location.getLatitude());
                        App.getInstance().mLocation = location;
                        break;

                    case 63:
                        startOfflineLocation();
                        break;
                    case 167://未开启定位权限
                        break;
                    case 62://`
                        restartLocation();
                        break;
                }
            }

        }
    }

}
