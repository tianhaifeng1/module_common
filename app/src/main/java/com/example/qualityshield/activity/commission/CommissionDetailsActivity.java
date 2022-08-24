package com.example.qualityshield.activity.commission;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;

import com.example.module_common.adapter.TViewPagerStateAdapter;
import com.example.module_common.fragment.TFragment;
import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.databinding.ActivityCommissionDetailsBinding;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;

import java.util.ArrayList;
import java.util.List;


public class CommissionDetailsActivity extends OfficialMVPActivity<HomeView,HomePresenter,ActivityCommissionDetailsBinding> {

    private List<TFragment> fragments;

    int index = 0;

    @Override
    protected ActivityCommissionDetailsBinding getViewBinding() {
        return ActivityCommissionDetailsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("代办详情", true);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        mBinding.smartRefresh.setOnMultiListener(new OnMultiListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(fragments!=null){
                    fragments.get(index).mPageNum++;
                    fragments.get(index).initData();
                }
//                refreshLayout.finishLoadMore();//结束加载
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(fragments!=null){
                    fragments.get(index).mPageNum = 1;
                    fragments.get(index).initData();
                }
//                refreshLayout.finishRefresh();//结束刷新
            }
        });

        mBinding.viewpager.setOffscreenPageLimit(3);
        //配置viewpager
        initFragmentViewData();
    }

    private void initFragmentViewData() {
        List<String> stringList = getTabTitle();
        for (int i = 0; i < stringList.size(); i++) {
            mBinding.tablayout.addTab(mBinding.tablayout.newTab().setText(stringList.get(i)));
        }

        fragments = new ArrayList<>();
        CommissionOneFragment allFragment = new CommissionOneFragment();
        fragments.add(allFragment);
        CommissionTwoFragment allFragment2 = new CommissionTwoFragment();
        fragments.add(allFragment2);
        CommissionThreeFragment allFragment3 = new CommissionThreeFragment();
        fragments.add(allFragment3);

        TViewPagerStateAdapter<TFragment> adapter = new TViewPagerStateAdapter(getSupportFragmentManager(), fragments, stringList);
        mBinding.viewpager.setAdapter(adapter);
        mBinding.tablayout.setupWithViewPager(mBinding.viewpager);
        mBinding.viewpager.setCurrentItem(index, false);
        mBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("=============tab : ", tab.getPosition()+"");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (index != position) {
                    index = position;
                    fragments.get(index).initData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<String> getTabTitle() {
        List<String> stringList = new ArrayList<>();
        stringList.add("详情");
        stringList.add("流程");
        stringList.add("执行记录");
        return stringList;
    }
}