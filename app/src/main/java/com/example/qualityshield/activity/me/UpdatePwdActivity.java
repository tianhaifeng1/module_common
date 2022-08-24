package com.example.qualityshield.activity.me;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.module_common.util.SharedPreferencesUtils;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.databinding.ActivityUpdatePwdBinding;

public class UpdatePwdActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityUpdatePwdBinding> {

    @Override
    protected ActivityUpdatePwdBinding getViewBinding() {
        return ActivityUpdatePwdBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("修改密码", true);
        titleModule.setTitleBottomLineShow(false);

        mBinding.loginSubmit.setOnClickListener(v -> {
            if (!mBinding.pwdOld.getText().toString().trim().equals(DemoConstant.userPwd)) {
                ToastUtils.showShort("旧密码错误");
            } else if (!mBinding.pwdNew.getText().toString().trim().equals(mBinding.pwdQueren.getText().toString().trim())) {
                ToastUtils.showShort("两次密码不一致");
            } else {
                ToastUtils.showShort("修改成功");
                DemoConstant.userPwd = mBinding.pwdQueren.getText().toString().trim();
                SharedPreferencesUtils.setParam(context, DemoConstant.user_pwd, mBinding.pwdQueren.getText().toString().trim());
                finish();
            }
        });
    }
}