package com.example.qualityshield.activity.homepage;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.TransferAdapter;
import com.example.qualityshield.databinding.ActivityTransferListBinding;

import java.util.ArrayList;
import java.util.List;


public class TransferListActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityTransferListBinding> {

    @Override
    protected ActivityTransferListBinding getViewBinding() {
        return ActivityTransferListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("转运列表", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        initAdapter();
    }

    private TransferAdapter transferAdapter;

    private void initAdapter() {
        mBinding.transferRv.setLayoutManager(new LinearLayoutManager(context));
        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        transferAdapter = new TransferAdapter(lists);
        transferAdapter.setOnItemClickListener((adapter, view, position) -> {
            skipActivity(TransferActivity.class);
        });
        mBinding.transferRv.setAdapter(transferAdapter);
    }
}