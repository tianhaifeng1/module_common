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
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import java.io.File;
import java.util.ArrayList;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

public class QualityTestingToActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityQualityTestingToBinding> implements BGASortableNinePhotoLayout.Delegate, View.OnClickListener {

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
            mBinding.settledAddPhotos.setMaxItemCount(9);
            mBinding.settledAddPhotos.setEditable(true);
            mBinding.settledAddPhotos.setPlusEnable(true);
            mBinding.settledAddPhotos.setSortable(false);
            // 设置拖拽排序控件的代理
            mBinding.settledAddPhotos.setDelegate(this);
        } else {
            titleModule.initTitle("质检详情", true);
            mBinding.settledAddPhotos.setMaxItemCount(9);
            mBinding.settledAddPhotos.setEditable(false);
            mBinding.settledAddPhotos.setPlusEnable(false);
            mBinding.settledAddPhotos.setSortable(false);
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

        mBinding.qualityTestingLin.setRadius(15);
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
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mBinding.settledAddPhotos.removeItem(position);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mBinding.settledAddPhotos.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, 101);
    }

    private void choicePhotoWrapper() {
        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

        Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                .maxChooseCount(mBinding.settledAddPhotos.getMaxItemCount() - mBinding.settledAddPhotos.getItemCount()) // 图片选择张数的最大值
                .selectedPhotos(null) // 当前已选中的图片路径集合
                .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                .build();
        startActivityForResult(photoPickerIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    // 结果回调
                    mBinding.settledAddPhotos.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
                    break;
                case 101:
                    mBinding.settledAddPhotos.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
                    break;
                default:
                    break;
            }
        }
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