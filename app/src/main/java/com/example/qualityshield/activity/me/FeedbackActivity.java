package com.example.qualityshield.activity.me;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.ComplaintAdapter;
import com.example.qualityshield.bean.ComplaintTypeBean;
import com.example.qualityshield.databinding.ActivityFeedbackBinding;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

public class FeedbackActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityFeedbackBinding> implements BGASortableNinePhotoLayout.Delegate {

    private List<ComplaintTypeBean> types = new ArrayList<>();

    @Override
    protected ActivityFeedbackBinding getViewBinding() {
        return ActivityFeedbackBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("意见反馈", true);
        titleModule.setTitleBottomLineShow(false);

        types.add(new ComplaintTypeBean("认证问题", true));
        types.add(new ComplaintTypeBean("上传资料", false));
        types.add(new ComplaintTypeBean("费用问题", false));

        mBinding.conmplaintContextLin.setRadius(15);

        mBinding.settledAddPhotos.setMaxItemCount(3);
        mBinding.settledAddPhotos.setEditable(true);
        mBinding.settledAddPhotos.setPlusEnable(true);
        mBinding.settledAddPhotos.setSortable(false);
        // 设置拖拽排序控件的代理
        mBinding.settledAddPhotos.setDelegate(this);

        TextChange textChange = new TextChange();
        mBinding.showEdit.addTextChangedListener(textChange);

        initAdapter();
    }

    private ComplaintAdapter complaintAdapter;
    private int index = 0;

    private void initAdapter() {
        mBinding.complaintRecl.setLayoutManager(new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        complaintAdapter = new ComplaintAdapter(types);
        complaintAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (index != position) {
                    List<ComplaintTypeBean> list = (List<ComplaintTypeBean>) adapter.getData();
                    list.get(index).setSelect(false);
                    list.get(position).setSelect(true);
                    complaintAdapter.notifyItemChanged(index);
                    complaintAdapter.notifyItemChanged(position);
                    index = position;
                }
            }
        });

        mBinding.complaintRecl.setAdapter(complaintAdapter);
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