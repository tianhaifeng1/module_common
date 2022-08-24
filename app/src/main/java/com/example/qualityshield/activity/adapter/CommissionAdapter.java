package com.example.qualityshield.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;

import java.util.List;

public class CommissionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CommissionAdapter(@Nullable List<String> data) {
        super(R.layout.item_commission, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
