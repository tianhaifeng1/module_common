package com.example.qualityshield.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;

import java.util.List;

public class TransferAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TransferAdapter(@Nullable List<String> data) {
        super(R.layout.item_transfer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        
    }
}