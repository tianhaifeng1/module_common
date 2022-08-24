package com.example.qualityshield.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;

import java.util.List;

public class QualityTestingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int type;

    public QualityTestingAdapter(int type, @Nullable List<String> data) {
        super(R.layout.item_quality_testing, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_quality_testing_name, item);
        if(type == 1){
            helper.setText(R.id.rolling_process, "等待质检");
        }else{
            helper.setText(R.id.rolling_process, "质检完成");
        }
    }
}