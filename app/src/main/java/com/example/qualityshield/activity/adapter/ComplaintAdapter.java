package com.example.qualityshield.activity.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ColorUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;
import com.example.qualityshield.bean.ComplaintTypeBean;

import java.util.List;

public class ComplaintAdapter extends BaseQuickAdapter<ComplaintTypeBean, BaseViewHolder> {

    public ComplaintAdapter(@Nullable List<ComplaintTypeBean> data) {
        super(R.layout.item_complaint, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ComplaintTypeBean item) {
        helper.setText(R.id.item_complaint_title, item.getTitle());
        TextView textView = helper.getView(R.id.item_complaint_title);
        if(item.isSelect()){
            textView.setTextColor(ColorUtils.getColor(R.color.color_white));
            textView.setBackgroundResource(R.drawable.r_16_blue_376bff);
        }else{
            textView.setTextColor(ColorUtils.getColor(R.color.black_800000));
            textView.setBackgroundResource(R.drawable.r_gray_f7f7f7);
        }
    }
}