package com.js.driver.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.js.driver.App;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huyg on 2019-05-30.
 */
public class AppUtils {

    private static DecimalFormat df = new DecimalFormat("#####0.0");

    public static String getDistance(LatLng latLng) {
        double distance = 0;
        if (App.getInstance() != null) {
            distance = DistanceUtil.getDistance(latLng, new LatLng(App.getInstance().mLocation.getLatitude(), App.getInstance().mLocation.getLongitude()));
        }
        return distance > 1000 ? df.format(distance / 1000) + " Km" : distance + "m";
    }

    public static boolean isMoney(String money) {
        String regex = "(^[1-9]([0-9]+)?(\\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)";
        return money.matches(regex);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone(Context context, String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            Toast.makeText(context, "手机号码为空", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + phoneNum);
            intent.setData(data);
            context.startActivity(intent);
        }
    }

    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
