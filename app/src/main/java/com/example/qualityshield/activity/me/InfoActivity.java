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

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;

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

            Intent photoPickerIntent1 = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(takePhotoDir1) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(1) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent1, 100);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    // 结果回调
                    GlideUtile.bindImageViewRound(context, BGAPhotoPickerActivity.getSelectedPhotos(data).get(0), mBinding.infoHead);
                    break;
                default:
                    break;
            }
        }
    }
}