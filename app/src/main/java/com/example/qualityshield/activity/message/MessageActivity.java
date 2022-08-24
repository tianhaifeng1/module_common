package com.example.qualityshield.activity.message;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.MessageAdapter;
import com.example.qualityshield.assist.DemoUtils;
import com.example.qualityshield.databinding.ActivityMessageBinding;

public class MessageActivity  extends OfficialMVPActivity<HomeView,HomePresenter, ActivityMessageBinding> {

    @Override
    protected ActivityMessageBinding getViewBinding() {
        return ActivityMessageBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("消息", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        initAdapter();
    }

    private MessageAdapter messageAdapter;

    private void initAdapter() {
        mBinding.messageRv.setLayoutManager(new LinearLayoutManager(context));
        messageAdapter = new MessageAdapter(DemoUtils.getList());
        mBinding.messageRv.setAdapter(messageAdapter);
    }
}