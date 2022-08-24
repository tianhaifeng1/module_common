package com.example.qualityshield.activity;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.databinding.ActivityLoginBinding;


public class LoginActivity extends OfficialMVPActivity<HomeView,HomePresenter, ActivityLoginBinding> implements View.OnClickListener {

    private int loginType = 1;

    @Override
    protected ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        SpanUtils.with(mBinding.loginTypeBtn)
                .append("没有账号去注册").setUnderline()
                .create();
        mBinding.loginEditPhone.setText(DemoConstant.userName);
        mBinding.loginEditPwd.setText(DemoConstant.userPwd);
        mBinding.loginEditPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

        mBinding.loginSubmit.setOnClickListener(this);
        mBinding.loginTypeBtn.setOnClickListener(this);
        mBinding.loginType1.setOnClickListener(this);
        mBinding.loginType2.setOnClickListener(this);
    }

    @Override
    protected boolean backBefore() {
        ToastUtils.showShort("再次点击退出系统");
        activityManager.exitApp(true);
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_submit:
                if (!mBinding.loginEditPhone.getText().toString().trim().equals(DemoConstant.userName) || !mBinding.loginEditPwd.getText().toString().trim().equals(DemoConstant.userPwd)) {
                    ToastUtils.showShort("账号或密码错误");
                    return;
                }
                if (loginType == 1) {
                    skipActivity(MainNavActivity.class);
                } else {
                    skipActivity(VisitorNavActivity.class);
                }
                break;
            case R.id.login_type_btn:
                skipActivity(RegisterActivity.class);
                break;
            case R.id.login_type1:
                //简陋的变换样式,绑定登录类型
                loginType = 1;
                mBinding.loginType1.setTextColor(ColorUtils.getColor(R.color.tab_checked));
                mBinding.loginType2.setTextColor(ColorUtils.getColor(R.color.color_name));
                break;
            case R.id.login_type2:
                //简陋的变换样式,绑定登录类型
                loginType = 2;
                mBinding.loginType1.setTextColor(ColorUtils.getColor(R.color.color_name));
                mBinding.loginType2.setTextColor(ColorUtils.getColor(R.color.tab_checked));
                break;
        }
    }
}