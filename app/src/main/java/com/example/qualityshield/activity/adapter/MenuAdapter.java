package com.example.qualityshield.activity.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.module_common.util.GlideUtile;
import com.example.qualityshield.R;
import com.example.qualityshield.bean.MenuBean;

import java.util.List;

public class MenuAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {

    public MenuAdapter(@Nullable List<MenuBean> data) {
        super(R.layout.item_fm_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean item) {
        int width=(ScreenUtils.getScreenWidth()-4)/3;
        int height=width;
        LinearLayout linearLayout = helper.getView(R.id.menu_rel);
        ViewGroup.LayoutParams params =  linearLayout.getLayoutParams();
        params.width = width;
        params.height = height;
        linearLayout.setLayoutParams(params);
        helper.setText(R.id.item_menu_name, item.getMenuName());
        GlideUtile.bindImageView(getContext(), item.getMenuIcon(), helper.getView(R.id.item_menu_icon));
    }
}
