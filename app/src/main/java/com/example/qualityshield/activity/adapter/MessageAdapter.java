package com.example.qualityshield.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MessageAdapter(@Nullable List<String> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull String item) {
        if(getItemPosition(item) == 1 || getItemPosition(item) == 2){
            helper.setText(R.id.item_message_type, "状态：未读");
            helper.setText(R.id.item_message_title, "系统消息");
        }else{
            helper.setText(R.id.item_message_type, "状态：已读");
            helper.setText(R.id.item_message_title, "广播消息");
        }
    }
}