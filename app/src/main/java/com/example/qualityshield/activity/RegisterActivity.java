package com.example.qualityshield.activity;

import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.databinding.ActivityRegisterBinding;


public class RegisterActivity extends OfficialMVPActivity<HomeView,HomePresenter, ActivityRegisterBinding> {

    @Override
    protected ActivityRegisterBinding getViewBinding() {
        return ActivityRegisterBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("注册账号", true);
        titleModule.setTitleBottomLineShow(false);

        mBinding.registerSubmit.setOnClickListener(v -> {
            if (mBinding.registerName.getText().toString().trim().equals("")) {
                ToastUtils.showShort("请输入姓名");
            } else if (mBinding.registerTel.getText().toString().trim().equals("")) {
                ToastUtils.showShort("请输入电话号");
            } else if (!RegexUtils.isMobileSimple(mBinding.registerTel.getText().toString().trim())) {
                ToastUtils.showShort("请输入正确的电话号");
            } else if (mBinding.registerPwd.getText().toString().trim().equals("")) {
                ToastUtils.showShort("请输入密码");
            } else if (mBinding.registerPwdto.getText().toString().trim().equals("")) {
                ToastUtils.showShort("请输入确认密码");
            } else if (!mBinding.registerPwdto.getText().toString().trim().equals(mBinding.registerPwd.getText().toString().trim())) {
                ToastUtils.showShort("两次密码不一致");
            } else if (mBinding.registerIdcard.getText().toString().trim().equals("")) {
                ToastUtils.showShort("请输入身份证号码");
            } else if (mBinding.registerEmail.getText().toString().trim().equals("")) {
                ToastUtils.showShort("请输入邮箱地址");
            } else if (!RegexUtils.isEmail(mBinding.registerEmail.getText().toString().trim())) {
                ToastUtils.showShort("请输入正确的邮箱地址");
            } else if (mBinding.registerCompany.getText().toString().trim().equals("")) {
                ToastUtils.showShort("请输入企业名称");
            }
            ToastUtils.showShort("注册功能正在完善中");
        });
    }
}