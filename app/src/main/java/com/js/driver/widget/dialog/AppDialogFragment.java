package com.js.driver.widget.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.js.driver.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by huyg on 2019/1/10.
 */
public class AppDialogFragment extends DialogFragment {

    @BindView(R.id.dialog_title)
    TextView mTitle;
    @BindView(R.id.dialog_message)
    TextView mMessage;
    @BindView(R.id.dialog_negative)
    TextView mNegative;
    @BindView(R.id.dialog_positive)
    TextView mPositive;

    @OnClick({R.id.dialog_negative, R.id.dialog_positive})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_negative:
                dismiss();
                if (mNegativeButtonListener != null) {
                    mNegativeButtonListener.onClick(mNegative);
                }
                break;
            case R.id.dialog_positive:
                dismiss();
                if (mPositiveButtonListener != null) {
                    mPositiveButtonListener.onClick(mPositive);
                }
                break;
        }
    }

    private Unbinder unbinder;
    private String title;
    private String message;
    private String positive;
    private String negative;
    private View.OnClickListener mNegativeButtonListener;
    private View.OnClickListener mPositiveButtonListener;


    public static AppDialogFragment getInstance() {
        AppDialogFragment dialogFragment = new AppDialogFragment();
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_app, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mMessage.setText(message);
        }

        if (!TextUtils.isEmpty(positive)) {
            mPositive.setText(positive);
        }

        if (!TextUtils.isEmpty(negative)) {
            mNegative.setText(negative);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNegativeButton(String negative, View.OnClickListener listener) {
        this.negative = negative;
        this.mNegativeButtonListener = listener;
    }

    public void setPositiveButton(String positive, View.OnClickListener listener) {
        this.positive = positive;
        this.mPositiveButtonListener = listener;
    }
}
