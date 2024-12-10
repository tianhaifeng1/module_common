package com.example.qualityshield.activity.homepage;

import android.content.Intent;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.assist.DemoUtils;
import com.example.qualityshield.bean.ProcessListBean;
import com.example.qualityshield.databinding.ActivityQualityTestingToBinding;
import com.example.qualityshield.dialog.RemindDialog;

import java.io.File;
import java.util.ArrayList;


public class QualityTestingToActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityQualityTestingToBinding> implements View.OnClickListener {

    private int type;

    @Override
    protected ActivityQualityTestingToBinding getViewBinding() {
        return ActivityQualityTestingToBinding.inflate(getLayoutInflater());
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
            titleModule.initTitle("质检", true);
        } else {
            titleModule.initTitle("质检详情", true);
            mBinding.qualityTestingLin.setVisibility(View.GONE);
            mBinding.qualityTestingBtnlin.setVisibility(View.GONE);
            mBinding.qualityTestingText.setVisibility(View.VISIBLE);
        }

        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        TextChange textChange = new TextChange();
        mBinding.showEdit.addTextChangedListener(textChange);

        if (DemoConstant.processType == 0) {
            mBinding.qualityTestingProcessName.setText("压制蜡模");
        } else if (DemoConstant.processType == 1) {
            mBinding.qualityTestingProcessName.setText("蜡膜尺检");
        } else if (DemoConstant.processType == 2) {
            mBinding.qualityTestingProcessName.setText("蜡膜组焊");
        } else if (DemoConstant.processType == 3) {
            mBinding.qualityTestingProcessName.setText("面层");
        } else if (DemoConstant.processType == 4) {
            mBinding.qualityTestingProcessName.setText("加固");
        }

        mBinding.rollingInfo.setOnClickListener(this);
        mBinding.rollingProcess.setOnClickListener(this);
        mBinding.qualitySubmit.setOnClickListener(this);
        mBinding.qualityNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.quality_submit:
                RemindDialog remindDialog = new RemindDialog.Builder(context)
                        .setMessage("是否确认质检？")
                        .setAffirmText("确认")
                        .setCancleText("取消")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //确认质检
                                ToastUtils.showShort("质检成功!");
                                ProcessListBean processListBeans = (ProcessListBean) DemoConstant.baseNodes.get(DemoConstant.processType);
                                ProcessListBean.ProcessChildBean bean = (ProcessListBean.ProcessChildBean) processListBeans.getChildNode().get(3);
                                bean.setValue("质检通过");
                                ProcessListBean.ProcessChildBean bean1 = (ProcessListBean.ProcessChildBean) processListBeans.getChildNode().get(4);
                                bean1.setValue(mBinding.showEdit.getText().toString().trim());
                                DemoConstant.type = 0;
                                if (DemoConstant.processType != 4) {
                                    DemoConstant.processType++;
                                } else {
                                    DemoConstant.processType = 0;
                                }
                                finish();
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
                break;
            case R.id.quality_no:
                RemindDialog remindDialog2 = new RemindDialog.Builder(context)
                        .setMessage("是否确认驳回？")
                        .setAffirmText("确认")
                        .setCancleText("取消")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //确认领卡
                                ToastUtils.showShort("驳回成功!");
                                DemoConstant.type = 0;
                                DemoConstant.processType = 0;
                                DemoConstant.baseNodes = DemoUtils.getEntity();
                                finish();
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog2.show(getSupportFragmentManager(), "dialog_edit_remind");
                break;
        }
    }

    //EditText的监听器
    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mBinding.showNum.setText(mBinding.showEdit.length() + "/200");
        }
    }
}