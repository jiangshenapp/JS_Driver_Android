package com.js.driver.util;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.js.driver.App;

import java.text.DecimalFormat;

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


}
