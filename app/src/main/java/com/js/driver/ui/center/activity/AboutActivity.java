package com.js.driver.ui.center.activity;

import android.content.Context;
import android.content.Intent;

import com.js.driver.R;
import com.js.frame.view.SimpleActivity;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/05/22
 * desc   :
 * version: 3.0.0
 */
public class AboutActivity extends SimpleActivity {

    public static void action(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {

    }

    @Override
    public void setActionBar() {
        mTitle.setText("关于");
    }
}
