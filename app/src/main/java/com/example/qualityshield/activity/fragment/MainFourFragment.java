package com.example.qualityshield.activity.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPFragment;
import com.example.module_common.util.DataCleanManager;
import com.example.module_common.util.GlideUtile;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.LoginActivity;
import com.example.qualityshield.activity.MainNavActivity;
import com.example.qualityshield.activity.me.BluetoothActivity;
import com.example.qualityshield.activity.me.FeedbackActivity;
import com.example.qualityshield.activity.me.InfoActivity;
import com.example.qualityshield.activity.me.SettingActivity;
import com.example.qualityshield.activity.me.UpdatePwdActivity;
import com.example.qualityshield.databinding.FragmentFmFourBinding;
import com.example.qualityshield.databinding.FragmentHomeBinding;
import com.example.qualityshield.dialog.RemindDialog;


public class MainFourFragment extends OfficialMVPFragment implements View.OnClickListener {

    private MainNavActivity navActivity;

    private FragmentFmFourBinding mBinding;

    @Override
    public void bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mBinding = FragmentFmFourBinding.inflate(inflater);
    }

    @Override
    public View getLayoutId() {
        return mBinding.getRoot();
    }

    @Override
    protected void initFragmentView(View view) {
        navActivity = (MainNavActivity) getActivity();

        GlideUtile.bindImageViewRound(activity.context, R.drawable.fm_logo, mBinding.fmFourRelHead);

        mBinding.fmFourInfo.setOnClickListener(this);
        mBinding.fmFourPwd.setOnClickListener(this);
        mBinding.fmFourHead.setOnClickListener(this);
        mBinding.settingEdit.setOnClickListener(this);
        mBinding.fmFourFeedback.setOnClickListener(this);
        mBinding.fmFourQlhc.setOnClickListener(this);
        mBinding.fmFourBluetooth.setOnClickListener(this);
        mBinding.fmFourSetting.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();

        //获取目录缓存
        String sizeCache = "0 B";
        try {
            sizeCache = DataCleanManager.getTotalCacheSize(activity.context);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mBinding.settingCache.setText(sizeCache);
        }
    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fm_four_info:
                //个人信息
//                activity.skipActivity(LoginActivity.class);
                break;
            case R.id.fm_four_pwd:
                //修改密码
                activity.skipActivity(UpdatePwdActivity.class);
                break;
            case R.id.fm_four_head:
                //个人信息
                activity.skipActivity(InfoActivity.class);
                break;
            case R.id.fm_four_feedback:
                //意见反馈
                activity.skipActivity(FeedbackActivity.class);
                break;
            case R.id.fm_four_bluetooth:
                //蓝牙连接
                activity.skipActivity(BluetoothActivity.class);
                break;
            case R.id.fm_four_setting:
                //设置
                activity.skipActivity(SettingActivity.class);
                break;
            case R.id.fm_four_qlhc:
                //清除缓存
                RemindDialog remindDialog2 = new RemindDialog.Builder(activity.context)
                        .setMessage("是否清除缓存？")
                        .setAffirmText("清空")
                        .setCancleText("取消")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                try {
                                    DataCleanManager.clearAllCache(activity.context);
                                    mBinding.settingCache.setText(DataCleanManager.getTotalCacheSize(activity.context));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog2.show(getFragmentManager(), "dialog_qchc_remind");
                break;
            case R.id.setting_edit:
                //退出登录：抹除一切缓存数据；同时抹除服务器相关配置数据（数据获取API地址，重新登陆后重新获取）
                RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                        .setMessage("是否确认退出登录？")
                        .setAffirmText("确认")
                        .setCancleText("取消")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //确认退出登录
                                activity.skipActivity(LoginActivity.class);
                                navActivity.finish();
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog.show(getFragmentManager(), "dialog_edit_remind");
                break;
        }
    }
}
