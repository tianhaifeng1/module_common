package com.example.qualityshield.activity.homepage;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.databinding.ActivityPaiGongBinding;

public class PaiGongActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityPaiGongBinding> {

    @Override
    protected ActivityPaiGongBinding getViewBinding() {
        return ActivityPaiGongBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
    }
}