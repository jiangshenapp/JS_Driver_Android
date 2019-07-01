package com.xlgcx.frame.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.xlgcx.frame.R;
import com.xlgcx.frame.R2;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Create by Weixf
 * Date on 2017/7/14
 * Description loading加载框
 */
public class CircleProgressDialog extends AlertDialog {
    @BindView(R2.id.view_indicator)
    AVLoadingIndicatorView mIndicator;

    private Context mContext;

    public CircleProgressDialog(Context context) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    public CircleProgressDialog(Context context, String text) {
        super(context, R.style.MyDialog);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount=0f;
        window.setAttributes(lp);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mIndicator != null && mIndicator.isShown()) {
            mIndicator.hide();
        }
    }

}
