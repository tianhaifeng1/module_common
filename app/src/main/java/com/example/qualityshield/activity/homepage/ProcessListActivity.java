package com.example.qualityshield.activity.homepage;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.ProcessAdapter;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.assist.RecycleViewDivider;
import com.example.qualityshield.databinding.ActivityProcessBinding;


public class ProcessListActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityProcessBinding> {

    @Override
    protected ActivityProcessBinding getViewBinding() {
        return ActivityProcessBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("工序列表", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        initAdapter();
    }

    private ProcessAdapter processAdapter;

    private void initAdapter() {
        mBinding.processRv.setLayoutManager(new LinearLayoutManager(context));
        mBinding.processRv.addItemDecoration(new RecycleViewDivider(
                context, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.color_white)));
        processAdapter = new ProcessAdapter();
        mBinding.processRv.setAdapter(processAdapter);
        processAdapter.setList(DemoConstant.baseNodes);
    }
}