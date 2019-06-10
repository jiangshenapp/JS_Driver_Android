package com.js.driver.widget.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.js.driver.App;
import com.js.driver.R;
import com.js.driver.model.event.AddDriverEvent;
import com.js.driver.util.UIUtil;
import com.xlgcx.http.ApiFactory;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyg on 2019/4/29.
 */
public class AddDriverFragment extends DialogFragment {


    @BindView(R.id.driver_phone)
    EditText mPhone;
    @BindView(R.id.driver_name)
    EditText mName;
    @BindView(R.id.driver_type)
    EditText mType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//取消对话框fragment的标题
        View view = inflater.inflate(R.layout.dialog_add_driver, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @OnClick({R.id.positive, R.id.negative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.positive:
                String phone = mPhone.getText().toString().trim();
                String name = mName.getText().toString().trim();
                String type = mType.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    UIUtil.toast("请输入手机号");
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    UIUtil.toast("请输入姓名");
                    return;
                }

                if (TextUtils.isEmpty(type)) {
                    UIUtil.toast("请输入驾照类型");
                    return;
                }
                EventBus.getDefault().post(new AddDriverEvent(name, phone, type));
                dismiss();
                break;
            case R.id.negative:
                dismiss();
                break;
        }
    }


}
