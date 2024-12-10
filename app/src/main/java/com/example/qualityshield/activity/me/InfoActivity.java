package com.example.qualityshield.activity.me;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.module_common.util.GlideUtile;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.databinding.ActivityInfoBinding;

import java.io.File;

public class InfoActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityInfoBinding> {

    @Override
    protected ActivityInfoBinding getViewBinding() {
        return ActivityInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("个人信息", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.initTitleMenu(2, "完成");
        titleModule.setMenuTextColor(R.color.color_name);

        mBinding.infoHeadLin.setOnClickListener(v -> {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir1 = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");
        });

        initData();
    }

    @Override
    public void onClickRightText(View view) {
        super.onClickRightText(view);
    }


    private void initData() {
        //获取个人信息
    }

}