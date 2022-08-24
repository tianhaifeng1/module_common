package com.example.qualityshield.activity.homepage;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.QualityTestingAdapter;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.databinding.ActivityQualityTestingBinding;

import java.util.ArrayList;
import java.util.List;


public class QualityTestingActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityQualityTestingBinding> {

    private int type;

    @Override
    protected ActivityQualityTestingBinding getViewBinding() {
        return ActivityQualityTestingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("质检列表", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        type = getIntent().getIntExtra("type", 0);

        initAdapter();
    }

    private QualityTestingAdapter qualityTestingAdapter;

    private void initAdapter() {
        mBinding.qualityTestingRv.setLayoutManager(new LinearLayoutManager(context));
        List<String> lists = new ArrayList<>();
        if (DemoConstant.processType == 0) {
            lists.add("压制蜡模");
        } else if (DemoConstant.processType == 1) {
            lists.add("蜡膜尺检");
        } else if (DemoConstant.processType == 2) {
            lists.add("蜡膜组焊");
        } else if (DemoConstant.processType == 3) {
            lists.add("面层");
        } else if (DemoConstant.processType == 4) {
            lists.add("加固");
        }
        qualityTestingAdapter = new QualityTestingAdapter(type, lists);
        qualityTestingAdapter.setOnItemClickListener((adapter, view, position) -> {
            //质检列表跳详情
            if(type == 1){
                Intent intent = new Intent(context, QualityTestingToActivity.class);
                intent.putExtra("type", type);
                skipActivity(intent);
            }else {
                Intent intent = new Intent(context, ProcessListActivity.class);
                skipActivity(intent);
            }
        });
        mBinding.qualityTestingRv.setAdapter(qualityTestingAdapter);
    }
}