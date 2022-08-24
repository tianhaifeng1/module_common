package com.example.qualityshield.activity.homepage;

import static com.example.module_common.titlemodule.TitleModule.MENU_IMAGE;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.activity.adapter.PopBrandAdapter;
import com.example.qualityshield.activity.adapter.QualityControlAdapter;
import com.example.qualityshield.assist.RecycleViewDivider;
import com.example.qualityshield.assist.SearchProgressBar;
import com.example.qualityshield.bean.QualityControlBean;
import com.example.qualityshield.bean.ScreenBean;
import com.example.qualityshield.databinding.ActivityDemoBinding;
import com.example.qualityshield.databinding.ActivityQualityControlBinding;

import java.util.ArrayList;
import java.util.List;


public class QualityControlActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityQualityControlBinding> implements View.OnClickListener {

    private List<QualityControlBean> qualityControlBeans = new ArrayList<>();
    private List<ScreenBean> screenBeans = new ArrayList<>();

    @Override
    protected ActivityQualityControlBinding getViewBinding() {
        return ActivityQualityControlBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("品质看板", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);
        titleModule.initTitleMenu(MENU_IMAGE);
        titleModule.setTitleMenuImage(R.drawable.more);

        mBinding.qualityControlSearchbar.setProgress(80);

        qualityControlBeans.add(new QualityControlBean("1", "遥控不了", "5%"));
        qualityControlBeans.add(new QualityControlBean("2", "面板杂质", "4%"));
        qualityControlBeans.add(new QualityControlBean("3", "色泽不够", "4%"));
        qualityControlBeans.add(new QualityControlBean("4", "面板杂质", "3%"));
        qualityControlBeans.add(new QualityControlBean("5", "模型缺损", "2%"));

        screenBeans.add(new ScreenBean(1, "建模", true));
        screenBeans.add(new ScreenBean(2, "浇筑", false));
        screenBeans.add(new ScreenBean(3, "加固", false));

        mBinding.qualityControlSort.setOnClickListener(this);
        mBinding.brandOk.setOnClickListener(this);

        initAdapter();
    }

    @Override
    public void onClickMenu(View view) {
        super.onClickMenu(view);
        mBinding.drawerLayout.openDrawer(mBinding.rightDrawerLayout);
    }

    private QualityControlAdapter qualityControlAdapter;
    private PopBrandAdapter popBrandAdapter;

    private int brandIndex = 0;

    private void initAdapter() {
        mBinding.qualityControlRv.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mBinding.qualityControlRv.addItemDecoration(new RecycleViewDivider(
                context, GridLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.color_line)));
        qualityControlAdapter = new QualityControlAdapter(qualityControlBeans);
        mBinding.qualityControlRv.setAdapter(qualityControlAdapter);


        mBinding.brandRecl.setLayoutManager(new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        popBrandAdapter = new PopBrandAdapter(screenBeans);
        popBrandAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (brandIndex != position) {
                List<ScreenBean> list = (List<ScreenBean>) adapter.getData();
                list.get(brandIndex).setSelect(false);
                list.get(position).setSelect(true);
                popBrandAdapter.notifyItemChanged(brandIndex);
                popBrandAdapter.notifyItemChanged(position);
                brandIndex = position;
            }
        });
        mBinding.brandRecl.setAdapter(popBrandAdapter);
    }

    private int type = 1;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quality_control_sort:
                qualityControlBeans.clear();
                if (type == 1) {
                    qualityControlBeans.add(new QualityControlBean("5", "模型缺损", "2%"));
                    qualityControlBeans.add(new QualityControlBean("4", "面板杂质", "3%"));
                    qualityControlBeans.add(new QualityControlBean("3", "色泽不够", "4%"));
                    qualityControlBeans.add(new QualityControlBean("2", "面板杂质", "4%"));
                    qualityControlBeans.add(new QualityControlBean("1", "遥控不了", "5%"));
                    type = 0;
                } else {
                    qualityControlBeans.add(new QualityControlBean("1", "遥控不了", "5%"));
                    qualityControlBeans.add(new QualityControlBean("2", "面板杂质", "4%"));
                    qualityControlBeans.add(new QualityControlBean("3", "色泽不够", "4%"));
                    qualityControlBeans.add(new QualityControlBean("4", "面板杂质", "3%"));
                    qualityControlBeans.add(new QualityControlBean("5", "模型缺损", "2%"));
                    type = 1;
                }
                qualityControlAdapter.notifyDataSetChanged();
                break;
            case R.id.brand_ok:
                mBinding.qualityControlName.setText(screenBeans.get(brandIndex).getCat_name() + "工序");
                mBinding.drawerLayout.closeDrawer(mBinding.rightDrawerLayout);
                break;
        }
    }
}