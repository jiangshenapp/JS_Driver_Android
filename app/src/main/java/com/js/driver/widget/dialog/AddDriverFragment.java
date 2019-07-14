package com.js.driver.widget.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.js.driver.R;
import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.event.AddDriverEvent;
import com.js.driver.util.AppUtils;
import com.js.driver.util.UIUtil;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.negative)
    TextView negative;

    public DriverBean driverBean;

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
        mPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (mPhone.getText().toString().length() == 11) {
                    EventBus.getDefault().post(new AddDriverEvent(1, mPhone.getText().toString()));
                } else {
                    mName.setText("");
                    mType.setText("");
                }
            }
        });
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
            case R.id.negative:
                String phone = mPhone.getText().toString().trim();
                String name = mName.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    UIUtil.toast("请输入手机号");
                    return;
                }

                if (!AppUtils.isMobile(phone)){
                    UIUtil.toast("请输入正确手机号");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    UIUtil.toast("姓名不能为空");
                    return;
                }
                EventBus.getDefault().post(new AddDriverEvent(2, driverBean.getDriverId()));
                break;
            case R.id.positive:
                dismiss();
                break;
        }
    }

    /**
     * 根据手机号反显姓名、驾照类型
     *
     * @param driverBean
     */
    public void setDriverBean(DriverBean driverBean) {
        this.driverBean = driverBean;
        mName.setText(driverBean.getDriverName());
        mType.setText(driverBean.getDriverLevel());
    }
}
