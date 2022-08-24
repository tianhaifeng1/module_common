package com.example.qualityshield.activity.homepage;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.WorkSheetAdapter;
import com.example.qualityshield.databinding.ActivityWorkSheetBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class WorkSheetActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityWorkSheetBinding> {

    @Override
    protected ActivityWorkSheetBinding getViewBinding() {
        return ActivityWorkSheetBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("工艺图纸", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        initAdapter();
    }

    private WorkSheetAdapter workSheetAdapter;

    private void initAdapter() {
        mBinding.workSheetRv.setLayoutManager(new GridLayoutManager(context, 2));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        workSheetAdapter = new WorkSheetAdapter(list);
        workSheetAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<String> list1 = new ArrayList<>();
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            list1.add(getResourcesUri(R.drawable.work_sheet_image1));
            Intent intent = new Intent(context, ImagePagerActivity.class);
            intent.putExtra("image_index", 0);
            intent.putExtra("image_urls", (Serializable) list1);
            skipActivity(intent);
        });
        mBinding.workSheetRv.setAdapter(workSheetAdapter);
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }
}