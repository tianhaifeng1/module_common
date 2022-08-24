package com.example.qualityshield.activity.adapter;

import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.module_common.util.GlideUtile;
import com.example.qualityshield.R;
import com.qmuiteam.qmui.layout.QMUIRelativeLayout;

import java.util.List;

public class WorkSheetAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public WorkSheetAdapter(@Nullable List<String> data) {
        super(R.layout.item_work_sheet, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int width = (ScreenUtils.getScreenWidth() - 15) / 2;
        double height = width * 0.8;
        QMUIRelativeLayout relativeLayout = helper.getView(R.id.work_sheet_rel);
        ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
        params.width = width;
        params.height = (int) height;
        relativeLayout.setLayoutParams(params);
        helper.setText(R.id.item_work_sheet_name, "发动机锻造杠杆A0001型");
        helper.setText(R.id.item_work_sheet_type, "加工图纸");
        helper.setText(R.id.item_work_sheet_process_name, "BMM-2030430");
        helper.setText(R.id.item_work_sheet_process_type, "锻造工序");
        GlideUtile.bindImageView(getContext(), getContext().getResources().getDrawable(R.drawable.work_sheet_image1), helper.getView(R.id.item_work_sheet_image));
    }
}