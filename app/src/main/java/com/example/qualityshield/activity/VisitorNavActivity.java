package com.example.qualityshield.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.base.NavBottomActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.fragment.MainFourFragment;
import com.example.qualityshield.activity.fragment.WorkBenchFragment;
import com.example.qualityshield.activity.visitorfragment.VisitorFragment;

public class VisitorNavActivity extends NavBottomActivity {


    @Override
    protected void initView() {
        super.initView();
//        smoothScroll = true;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_nav_bottom;
    }

    @Override
    protected void initFragmentData() {
        fragmentList.add(new VisitorFragment());
        fragmentList.add(new WorkBenchFragment());
        fragmentList.add(new MainFourFragment());
    }

    @Override
    protected int initTabMenu() {
        return R.menu.navigation_visitor;
    }

    @Override
    protected boolean backBefore() {
        ToastUtils.showShort("再次点击退出系统");
        activityManager.exitApp(true);
        return false;
    }

}