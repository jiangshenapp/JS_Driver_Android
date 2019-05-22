package com.js.driver.util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.js.driver.App;

import java.lang.reflect.Field;

/**
 * Created by chen on 20/03/2018.
 */

public class UIUtil {

    public static Toast toast;

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dip2px(float dipValue) {
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void toast(String message) {
        if (!TextUtils.isEmpty(message)) {
            if (toast == null) {
                toast = Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
            }
            toast.show();
        }
    }
}
