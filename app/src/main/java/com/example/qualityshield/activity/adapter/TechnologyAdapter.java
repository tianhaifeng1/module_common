package com.example.qualityshield.activity.adapter;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;
import com.example.qualityshield.bean.TechnologyBean;

import java.util.List;

public class TechnologyAdapter extends BaseQuickAdapter<TechnologyBean, BaseViewHolder> {

    public TechnologyAdapter(@Nullable List<TechnologyBean> data) {
        super(R.layout.item_technology, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TechnologyBean item) {
        if(getItemPosition(item) == 0){
            TextView textView1 = helper.getView(R.id.item_technology_name);
            TextView textView2 = helper.getView(R.id.item_technology_value);
            TextView textView3 = helper.getView(R.id.item_technology_content);
            textView1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textView2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textView3.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        helper.setText(R.id.item_technology_name, item.getName());
        helper.setText(R.id.item_technology_value, item.getValue());
        helper.setText(R.id.item_technology_content, item.getContent());
    }
}