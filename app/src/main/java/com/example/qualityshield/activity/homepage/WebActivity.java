package com.example.qualityshield.activity.homepage;

import android.view.View;
import android.widget.LinearLayout;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.databinding.ActivityWebBinding;
import com.just.agentweb.AgentWeb;

public class WebActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityWebBinding> {

    private AgentWeb mAgentWeb;

    @Override
    protected ActivityWebBinding getViewBinding() {
        return ActivityWebBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("外部链接", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) mBinding.webLin, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("https://www.baidu.com/");

//        mAgentWeb.getJsInterfaceHolder().addJavaObject("test",new AndroidtoJs());
    }
}