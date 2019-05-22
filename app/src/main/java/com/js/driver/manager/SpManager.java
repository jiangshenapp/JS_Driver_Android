package com.js.driver.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by huyg on 2019/4/21.
 */
public class SpManager {

    public static final String mTAG = "test";
    // 创建一个写入器
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SpManager mSpManager;

    // 构造方法
    public SpManager(Context context) {
        mPreferences = context.getSharedPreferences(mTAG, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    // 单例模式
    public static SpManager getInstance(Context context) {
        if (mSpManager == null) {
            mSpManager = new SpManager(context);
        }
        return mSpManager;
    }

    // 存入数据
    public void putSP(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void putIntSP(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    // 获取数据
    public String getSP(String key) {
        return mPreferences.getString(key, "");
    }

    public int getIntSP(String key) {
        return mPreferences.getInt(key, 0);
    }

    // 移除数据
    public void removeSP(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }

    // 移除所有数据
    public void clear() {
        mPreferences.edit().clear().commit();
    }
}
