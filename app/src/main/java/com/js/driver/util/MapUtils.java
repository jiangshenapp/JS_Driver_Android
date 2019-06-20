package com.js.driver.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyg on 2019/2/12.
 */
public class MapUtils {


    private static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


    public static void startBaidu(Context context, LatLng start, LatLng end, String dName) {
        if (isAvilible(context, "com.baidu.BaiduMap")) {
            String url = "baidumap://map/direction?&origin=" + start.latitude + "," + start.longitude + "&destination=name:" + dName + "|latlng:" + end.latitude + "," + end.longitude + "&mode=driving";
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context,"请先安装百度地图!",Toast.LENGTH_SHORT).show();
        }
    }

    public static void startGaode(Context context, LatLng start, LatLng end, String dName) {
        if (isAvilible(context, "com.autonavi.minimap")) {
            start = convertBaiduToGPS(start);
            end = convertBaiduToGPS(end);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.parse("androidamap://route?sourceApplication=jingkaicar&slat=" + start.latitude + "&slon=" + start.longitude + "&sname=" + "我的位置" + "&dlat=" + end.latitude + "&dlon=" + end.longitude + "&dname=" + dName + "&dev=0&t=0");
            intent.setData(uri);
            context.startActivity(intent);
        } else {
            Toast.makeText(context,"请先安装高德地图!",Toast.LENGTH_SHORT).show();
        }

    }


    public static LatLng convertBaiduToGPS(LatLng sourceLatLng) {
        double[] dou = GPSUtils.bd09_To_Gcj02(sourceLatLng.latitude, sourceLatLng.longitude);
        return new LatLng(dou[0], dou[1]);
    }
}
