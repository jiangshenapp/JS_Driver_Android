package com.js.driver.ui.message.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.js.driver.R;
import com.js.frame.view.SimpleActivity;

import butterknife.BindView;

/**
 * Created by huyg on 2019-07-08.
 */
public class EaseChatActivity extends SimpleActivity {


    @BindView(R.id.frame)
    FrameLayout frame;

    private String userId;
    private int chatType;


    public static void action(Context context, EMConversation emConversation) {
        Intent intent = new Intent(context, EaseChatActivity.class);
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, emConversation.getType());
        intent.putExtra(EaseConstant.EXTRA_USER_ID, emConversation.conversationId());
        context.startActivity(intent);
    }

    private EaseChatFragment mEaseChatFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_ease_chat;
    }

    @Override
    protected void init() {
        initIntent();
        initView();
    }

    private void initIntent() {
        chatType = getIntent().getIntExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        userId = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        mTitle.setText(userId);
    }

    private void initView() {
        mEaseChatFragment = new EaseChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID, userId);
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, chatType);
        mEaseChatFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame, mEaseChatFragment).commit();
    }

    @Override
    public void setActionBar() {

    }


}
