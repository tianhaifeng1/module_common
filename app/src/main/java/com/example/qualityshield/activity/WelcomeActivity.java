package com.example.qualityshield.activity;

import android.os.Handler;

import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.assist.DemoUtils;
import com.example.qualityshield.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends OfficialMVPActivity<HomeView,HomePresenter, ActivityWelcomeBinding> {

    private Handler handler;

    @Override
    protected ActivityWelcomeBinding getViewBinding() {
        return ActivityWelcomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
//        handler = new Handler();
        DemoConstant.baseNodes = DemoUtils.getEntity();
        DemoConstant.type = 0;
        DemoConstant.processType = 0;
        skip();
    }

    private void skip() {
        skipActivity(LoginActivity.class);
//        handler.postDelayed(() -> {
//            boolean isFirst = (Boolean) SharedPreferencesUtils.getParam(context, DemoConstant.user_first, true);
//            if (isFirst) {
//                skipActivity(GuideActivity.class);
//            } else {
//            skipActivity(MainNavActivity.class);
//            }
//        }, 1000);
    }
}