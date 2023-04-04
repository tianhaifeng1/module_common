package com.example.qualityshield.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.base.NavBottomActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.fragment.HomeFragment;
import com.example.qualityshield.activity.fragment.MainFourFragment;
import com.example.qualityshield.activity.fragment.WorkBenchFragment;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;

public class MainNavActivity extends NavBottomActivity {


    @Override
    protected void initView() {
        super.initView();

        smoothScroll = true;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_nav_bottom;
    }

    @Override
    protected void initFragmentData() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new WorkBenchFragment());
        fragmentList.add(new MainFourFragment());
    }

    @Override
    protected int initTabMenu() {
        return R.menu.navigation_main;
    }

    @Override
    protected boolean backBefore() {
        ToastUtils.showShort("再次点击退出系统");
        activityManager.exitApp(true);
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
                    Log.d("111", obj.originalValue);
                    ToastUtils.showShort("扫码结果===" + obj.originalValue);
                    break;
            }
        }
    }
}