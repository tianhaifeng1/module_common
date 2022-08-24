package com.example.qualityshield.activity.homepage;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.TechnologyAdapter;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.assist.DemoUtils;
import com.example.qualityshield.assist.RecycleViewDivider;
import com.example.qualityshield.bean.ProcessListBean;
import com.example.qualityshield.bean.TechnologyBean;
import com.example.qualityshield.databinding.ActivityRollingBinding;
import com.example.qualityshield.dialog.RemindDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RollingActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityRollingBinding> implements View.OnClickListener {

    private int type;
    private List<TechnologyBean> technologyBeans = new ArrayList<>();

    @Override
    protected ActivityRollingBinding getViewBinding() {
        return ActivityRollingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        type = getIntent().getIntExtra("type", 0);

        if (type == 1) {
            titleModule.initTitle("收卡", true);
        } else {
            titleModule.initTitle("报工", true);
        }

        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        if (type == 1) {
            mBinding.rollingSubmit.setText("确认收卡");
        } else {
            mBinding.rollingSubmit.setText("确认报工");
        }

        if (DemoConstant.processType == 0) {
            mBinding.rollingProcessName.setText("压制蜡模");
        } else if (DemoConstant.processType == 1) {
            mBinding.rollingProcessName.setText("蜡膜尺检");
        } else if (DemoConstant.processType == 2) {
            mBinding.rollingProcessName.setText("蜡膜组焊");
        } else if (DemoConstant.processType == 3) {
            mBinding.rollingProcessName.setText("面层");
        } else if (DemoConstant.processType == 4) {
            mBinding.rollingProcessName.setText("加固");
        }

        technologyBeans = DemoUtils.getTechnologyBeans();

        mBinding.rollingInfo.setOnClickListener(this);
        mBinding.rollingProcess.setOnClickListener(this);
        mBinding.rollingSubmit.setOnClickListener(this);
        mBinding.rollingImage.setOnClickListener(this);
        mBinding.rollingTechnology.setOnClickListener(this);

        initAdapter();
    }

    private TechnologyAdapter technologyAdapter;

    private void initAdapter() {
        mBinding.technologyRv.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mBinding.technologyRv.addItemDecoration(new RecycleViewDivider(
                context, GridLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.colorblack)));
        technologyAdapter = new TechnologyAdapter(technologyBeans);
        mBinding.technologyRv.setAdapter(technologyAdapter);
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rolling_technology:
                if (mBinding.technologyLin.getVisibility() == View.VISIBLE) {
                    mBinding.technologyLin.setVisibility(View.GONE);
                } else {
                    mBinding.technologyLin.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rolling_image:
                List<String> list = new ArrayList<>();
                list.add(getResourcesUri(R.drawable.image1));
                Intent intent = new Intent(context, ImagePagerActivity.class);
                intent.putExtra("image_index", 0);
                intent.putExtra("image_urls", (Serializable) list);
                skipActivity(intent);
                break;
            case R.id.rolling_info:
                if (mBinding.rollingInfolin.getVisibility() == View.VISIBLE) {
                    mBinding.rollingInfolin.setVisibility(View.GONE);
                } else {
                    mBinding.rollingInfolin.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rolling_process:
                skipActivity(ProcessListActivity.class);
                break;
            case R.id.rolling_submit:
                if (type == 1) {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setMessage("是否确认收卡？")
                            .setAffirmText("确认")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                                @Override
                                public void onRemindClickAffirm(View view) {
                                    //确认领卡
                                    DemoConstant.type = 1;
                                    ProcessListBean processListBeans = (ProcessListBean) DemoConstant.baseNodes.get(DemoConstant.processType);
                                    ProcessListBean.ProcessChildBean bean = (ProcessListBean.ProcessChildBean) processListBeans.getChildNode().get(3);
                                    bean.setValue("进行中");
                                    ToastUtils.showShort("收卡成功!");
                                    finish();
                                }

                                @Override
                                public void onRemindClickCancle(View view) {

                                }
                            }).create();
                    remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setMessage("请确认工序任务是否完成？")
                            .setAffirmText("确认")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                                @Override
                                public void onRemindClickAffirm(View view) {
                                    //确认报工
                                    DemoConstant.type = 2;
                                    ProcessListBean processListBeans = (ProcessListBean) DemoConstant.baseNodes.get(DemoConstant.processType);
                                    ProcessListBean.ProcessChildBean bean = (ProcessListBean.ProcessChildBean) processListBeans.getChildNode().get(3);
                                    bean.setValue("已报工，等待质检");
                                    ToastUtils.showShort("报工成功!");
                                    finish();
                                }

                                @Override
                                public void onRemindClickCancle(View view) {

                                }
                            }).create();
                    remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
                }
                break;
        }
    }
}