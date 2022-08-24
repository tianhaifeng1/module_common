package com.example.qualityshield.activity.homepage;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.databinding.ActivityTransferBinding;
import com.example.qualityshield.dialog.RemindDialog;


public class TransferActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityTransferBinding> {

    @Override
    protected ActivityTransferBinding getViewBinding() {
        return ActivityTransferBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("转运", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        mBinding.transferSubmit.setOnClickListener(v -> {
            RemindDialog remindDialog = new RemindDialog.Builder(context)
                    .setMessage("是否确认转运？")
                    .setAffirmText("确认")
                    .setCancleText("取消")
                    .setCancelable(true)
                    .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                        @Override
                        public void onRemindClickAffirm(View view) {
                            ToastUtils.showShort("转运成功");
                        }

                        @Override
                        public void onRemindClickCancle(View view) {

                        }
                    }).create();
            remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
        });
    }
}