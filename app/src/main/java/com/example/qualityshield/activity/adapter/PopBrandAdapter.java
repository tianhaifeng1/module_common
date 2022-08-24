package com.example.qualityshield.activity.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;
import com.example.qualityshield.bean.ScreenBean;

import java.util.List;

public class PopBrandAdapter extends BaseQuickAdapter<ScreenBean, BaseViewHolder> {

    public PopBrandAdapter(@Nullable List<ScreenBean> data) {
        super(R.layout.item_pop_brand, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScreenBean item) {
        helper.setText(R.id.item_brand_title, item.getCat_name());

        TextView tv = helper.getView(R.id.item_brand_title);

        if (item.isSelect()) {
            tv.setTextColor(getContext().getResources().getColor(R.color.color_white));
            tv.setBackground(getContext().getResources().getDrawable(R.drawable.item_brand_yes));
        } else {
            tv.setTextColor(getContext().getResources().getColor(R.color.color_name));
            tv.setBackground(getContext().getResources().getDrawable(R.drawable.item_brand_no));
        }
    }
}