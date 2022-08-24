package com.example.qualityshield.activity.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPFragment;
import com.example.module_common.util.GlideUtile;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.adapter.CommissionAdapter;
import com.example.qualityshield.activity.commission.CommissionDetailsActivity;
import com.example.qualityshield.assist.DemoUtils;
import com.example.module_common.util.ModuleUtils;
import com.example.qualityshield.databinding.FragmentHomeBinding;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;


/**
 * @ProjectName: QualityShield
 * @Package: com.example.qualityshield.activity.fragment
 * @ClassName: HomeFragment
 * @Description:
 * @Author: tian
 * @CreateDate: 2022/7/26 9:54
 */
public class HomeFragment extends OfficialMVPFragment {

    private FragmentHomeBinding mBinding;

    @Override
    public void bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mBinding = FragmentHomeBinding.inflate(inflater);
    }

    @Override
    public View getLayoutId() {
        return mBinding.getRoot();
    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }

    @Override
    protected void initFragmentView(View view) {
        mBinding.titleMenu1.setOnClickListener(v -> {
            ModuleUtils.callPhone(activity,"18092904363");
        });
        //初始化banner组件
        initBanner();

        initAdapter();
    }

    private CommissionAdapter commissionAdapter;

    private void initAdapter() {
        mBinding.fmTwoRv.setLayoutManager(new LinearLayoutManager(activity.context));
        commissionAdapter = new CommissionAdapter(DemoUtils.getList());
        commissionAdapter.setOnItemClickListener((adapter, view, position) -> activity.skipActivity(CommissionDetailsActivity.class));
        mBinding.fmTwoRv.setAdapter(commissionAdapter);
    }

    private void initBanner() {
        mBinding.homeBanner.setAdapter(new ImageAdapter(DemoUtils.getList()))
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setBannerRound(15)
                .setIndicator(new CircleIndicator(activity.context));
    }

    public class ImageAdapter extends BannerAdapter<String, ImageAdapter.BannerViewHolder> {

        public ImageAdapter(List<String> mDatas) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(mDatas);
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public ImageAdapter.BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return new ImageAdapter.BannerViewHolder(imageView);
        }

        @Override
        public void onBindView(ImageAdapter.BannerViewHolder holder, String data, int position, int size) {
            GlideUtile.bindImageView(activity.context, activity.getResources().getDrawable(R.drawable.home), holder.imageView);
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public BannerViewHolder(@NonNull ImageView view) {
                super(view);
                this.imageView = view;
            }
        }
    }
}
