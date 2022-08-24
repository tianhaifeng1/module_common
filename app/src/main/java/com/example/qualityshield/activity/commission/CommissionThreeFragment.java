package com.example.qualityshield.activity.commission;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPFragment;
import com.example.qualityshield.databinding.FragmentCommissionThreeBinding;

public class CommissionThreeFragment extends OfficialMVPFragment {

    private FragmentCommissionThreeBinding mBinding;

    @Override
    public void bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mBinding = FragmentCommissionThreeBinding.inflate(inflater);
    }

    @Override
    public View getLayoutId() {
        return mBinding.getRoot();
    }

    @Override
    protected void initFragmentView(View view) {

    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }
}
