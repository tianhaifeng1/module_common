package com.example.qualityshield.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;

import java.util.List;

public class TaskBoardAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TaskBoardAdapter(@Nullable List<String> data) {
        super(R.layout.item_task_board, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if(getItemPosition(item) == 0){
            helper.setText(R.id.item_task_board_btn, "收卡");
        }else{
            helper.setText(R.id.item_task_board_btn, "申请\n调度");
        }
    }
}