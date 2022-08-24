package com.example.qualityshield.activity.homepage;

import android.content.Intent;
import android.content.res.TypedArray;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.LoginActivity;
import com.example.qualityshield.activity.adapter.MenuAdapter;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.assist.RecycleViewDivider;
import com.example.qualityshield.bean.MenuBean;
import com.example.qualityshield.databinding.ActivityMoreMenuBinding;
import com.example.qualityshield.dialog.RemindDialog;

import java.util.ArrayList;
import java.util.List;


public class MoreMenuActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityMoreMenuBinding> {

    private List<MenuBean> menuBeans = new ArrayList<>();

    @Override
    protected ActivityMoreMenuBinding getViewBinding() {
        return ActivityMoreMenuBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("更多工具", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        //获取资源文件路径
        String[] stringarray1 = getResources().getStringArray(R.array.product_name);
        TypedArray item1 = getResources().obtainTypedArray(R.array.product_icon);
        for (int i = 0; i < stringarray1.length; i++) {
            MenuBean bean = new MenuBean(stringarray1[i], item1.getResourceId(i, 0));
            menuBeans.add(bean);
        }
        //初始化适配器
        initAdapter();
    }

    private MenuAdapter menuAdapter;

    private void initAdapter() {
        mBinding.menuRv.setLayoutManager(new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mBinding.menuRv.addItemDecoration(new RecycleViewDivider(
                context, GridLayoutManager.VERTICAL, 2, getResources().getColor(R.color.color_line)));
        mBinding.menuRv.addItemDecoration(new RecycleViewDivider(
                context, GridLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.color_line)));
        menuAdapter = new MenuAdapter(menuBeans);
        menuAdapter.setOnItemClickListener((adapter, view, position) -> {
            MenuBean menuBean = (MenuBean) adapter.getData().get(position);
            if (menuBean.getMenuName().equals("上机")) {
                //处理上机业务逻辑
            } else if (menuBean.getMenuName().equals("下机")) {
                RemindDialog remindDialog = new RemindDialog.Builder(context)
                        .setMessage("是否确认退出登录？")
                        .setAffirmText("确认")
                        .setCancleText("取消")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //确认退出登录
                                skipActivity(LoginActivity.class);
                                System.exit(0);
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
            } else if (menuBean.getMenuName().equals("收卡")) {
                if (DemoConstant.type == 0) {
                    Intent intent = new Intent(context, TaskBoardActivity.class);
                    skipActivity(intent);
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setMessage("暂未有可领卡的工序")
                            .setAffirmText("")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .create();
                    remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
                }
            } else if (menuBean.getMenuName().equals("报工")) {
                if (DemoConstant.type == 1) {
                    Intent intent = new Intent(context, RollingActivity.class);
                    intent.putExtra("type", 2);
                    skipActivity(intent);
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setMessage("暂未有可报工的工序")
                            .setAffirmText("")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .create();
                    remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
                }
            } else if (menuBean.getMenuName().equals("质检")) {
                if (DemoConstant.type == 2) {
                    Intent intent = new Intent(context, QualityTestingActivity.class);
                    intent.putExtra("type", 1);
                    skipActivity(intent);
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setMessage("暂未有待质检的工序")
                            .setAffirmText("")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .create();
                    remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
                }
            } else if (menuBean.getMenuName().equals("历史检验")) {
                Intent intent = new Intent(context, QualityTestingActivity.class);
                intent.putExtra("type", 2);
                skipActivity(intent);
            } else if (menuBean.getMenuName().equals("工艺图纸")) {
                Intent intent = new Intent(context, WorkSheetActivity.class);
                skipActivity(intent);
            } else if (menuBean.getMenuName().equals("品质看板")) {
                Intent intent = new Intent(context, QualityControlActivity.class);
                skipActivity(intent);
            } else if (menuBean.getMenuName().equals("转运")) {
                Intent intent = new Intent(context, TransferListActivity.class);
                skipActivity(intent);
            }
        });
        mBinding.menuRv.setAdapter(menuAdapter);
    }
}