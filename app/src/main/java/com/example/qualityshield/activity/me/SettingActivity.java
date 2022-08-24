package com.example.qualityshield.activity.me;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.module_common.util.SharedPreferencesUtils;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.databinding.ActivitySettingBinding;

import java.util.ArrayList;
import java.util.Arrays;


public class SettingActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivitySettingBinding> {

    @Override
    protected ActivitySettingBinding getViewBinding() {
        return ActivitySettingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("个人设置", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        if (DemoConstant.homeType.equals("")) {
            mBinding.settingModule1.setChecked(true);
            mBinding.settingModule2.setChecked(true);
            mBinding.settingModule3.setChecked(true);
        } else {
            String[] array = DemoConstant.homeType.split(",");
            ArrayList<String> types =
                    new ArrayList<String>(Arrays.asList(array));
            for (String type : types) {
                if (type.equals("1")) {
                    mBinding.settingModule1.setChecked(true);
                } else if (type.equals("2")) {
                    mBinding.settingModule2.setChecked(true);
                } else if (type.equals("3")) {
                    mBinding.settingModule3.setChecked(true);
                }
            }
        }

        mBinding.settingSubmit.setOnClickListener(v -> {
            String moduleType = "";
            if (mBinding.settingModule1.isChecked()) {
                moduleType += "1";
            }
            if (mBinding.settingModule2.isChecked()) {
                moduleType += ",2";
            }
            if (mBinding.settingModule3.isChecked()) {
                moduleType += ",3";
            }
            if(moduleType.equals("")){
                ToastUtils.showShort("请至少选择一项");
                return;
            }
            Log.i("moduleType===", moduleType);
            SharedPreferencesUtils.setParam(context, DemoConstant.home_type, moduleType);
            DemoConstant.homeType = moduleType;
            ToastUtils.showShort("保存成功");
            finish();
        });
    }
}