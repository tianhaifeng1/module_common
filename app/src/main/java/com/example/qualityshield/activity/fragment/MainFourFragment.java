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

        //??????????????????
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
                //????????????
//                activity.skipActivity(LoginActivity.class);
                break;
            case R.id.fm_four_pwd:
                //????????????
                activity.skipActivity(UpdatePwdActivity.class);
                break;
            case R.id.fm_four_head:
                //????????????
                activity.skipActivity(InfoActivity.class);
                break;
            case R.id.fm_four_feedback:
                //????????????
                activity.skipActivity(FeedbackActivity.class);
                break;
            case R.id.fm_four_bluetooth:
                //????????????
                activity.skipActivity(BluetoothActivity.class);
                break;
            case R.id.fm_four_setting:
                //??????
                activity.skipActivity(SettingActivity.class);
                break;
            case R.id.fm_four_qlhc:
                //????????????
                RemindDialog remindDialog2 = new RemindDialog.Builder(activity.context)
                        .setMessage("?????????????????????")
                        .setAffirmText("??????")
                        .setCancleText("??????")
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
                //????????????????????????????????????????????????????????????????????????????????????????????????API???????????????????????????????????????
                RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                        .setMessage("???????????????????????????")
                        .setAffirmText("??????")
                        .setCancleText("??????")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //??????????????????
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
