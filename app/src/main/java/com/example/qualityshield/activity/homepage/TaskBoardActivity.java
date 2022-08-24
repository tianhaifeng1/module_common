package com.example.qualityshield.activity.homepage;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.TaskBoardAdapter;
import com.example.qualityshield.databinding.ActivityTaskBoardBinding;
import com.example.qualityshield.dialog.RemindDialog;

import java.util.ArrayList;
import java.util.List;

public class TaskBoardActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityTaskBoardBinding> {

    @Override
    protected ActivityTaskBoardBinding getViewBinding() {
        return ActivityTaskBoardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("任务看板", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        initAdapter();
    }

    private TaskBoardAdapter taskBoardAdapter;

    private void initAdapter() {
        mBinding.taskBoardRv.setLayoutManager(new LinearLayoutManager(context));
        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        taskBoardAdapter = new TaskBoardAdapter(lists);
        // 先注册需要点击的子控件id（注意，请不要写在convert方法里）
        taskBoardAdapter.addChildClickViewIds(R.id.item_task_board_btn);
        // 设置子控件点击监听
        taskBoardAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.item_task_board_btn) {
                if (position == 0) {
                    Intent intent = new Intent(context, RollingActivity.class);
                    intent.putExtra("type", 1);
                    skipActivity(intent);
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setMessage("是否确认申请调度？")
                            .setAffirmText("确认")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                                @Override
                                public void onRemindClickAffirm(View view) {
                                    ToastUtils.showShort("申请调度成功");
                                }

                                @Override
                                public void onRemindClickCancle(View view) {

                                }
                            }).create();
                    remindDialog.show(getSupportFragmentManager(), "dialog_edit_remind");
                }
            }
        });
        mBinding.taskBoardRv.setAdapter(taskBoardAdapter);
    }
}