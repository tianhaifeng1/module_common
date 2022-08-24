package com.example.qualityshield.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;
import com.example.qualityshield.bean.QualityControlBean;

import java.util.List;

public class QualityControlAdapter extends BaseQuickAdapter<QualityControlBean, BaseViewHolder> {

    public QualityControlAdapter(@Nullable List<QualityControlBean> data) {
        super(R.layout.item_quality_control, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QualityControlBean item) {
        helper.setText(R.id.item_quality_control_count, item.getCount());
        helper.setText(R.id.item_quality_control_name, item.getName());
        helper.setText(R.id.item_quality_control_proportion, item.getProportion());
    }
}
