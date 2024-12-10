package com.example.qualityshield.activity.me;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.ComplaintAdapter;
import com.example.qualityshield.bean.ComplaintTypeBean;
import com.example.qualityshield.databinding.ActivityFeedbackBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityFeedbackBinding>  {

    private List<ComplaintTypeBean> types = new ArrayList<>();

    @Override
    protected ActivityFeedbackBinding getViewBinding() {
        return ActivityFeedbackBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("意见反馈", true);
        titleModule.setTitleBottomLineShow(false);

        types.add(new ComplaintTypeBean("认证问题", true));
        types.add(new ComplaintTypeBean("上传资料", false));
        types.add(new ComplaintTypeBean("费用问题", false));

        TextChange textChange = new TextChange();
        mBinding.showEdit.addTextChangedListener(textChange);

        initAdapter();
    }

    private ComplaintAdapter complaintAdapter;
    private int index = 0;

    private void initAdapter() {
        mBinding.complaintRecl.setLayoutManager(new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        complaintAdapter = new ComplaintAdapter(types);
        complaintAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (index != position) {
                    List<ComplaintTypeBean> list = (List<ComplaintTypeBean>) adapter.getData();
                    list.get(index).setSelect(false);
                    list.get(position).setSelect(true);
                    complaintAdapter.notifyItemChanged(index);
                    complaintAdapter.notifyItemChanged(position);
                    index = position;
                }
            }
        });

        mBinding.complaintRecl.setAdapter(complaintAdapter);
    }

    //EditText的监听器
    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mBinding.showNum.setText(mBinding.showEdit.length() + "/200");
        }
    }
}