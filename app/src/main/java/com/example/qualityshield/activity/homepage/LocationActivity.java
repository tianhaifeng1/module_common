package com.example.qualityshield.activity.homepage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.model.EventModel;
import com.example.module_common.model.LocationModel;
import com.example.module_common.util.ModuleUtils;
import com.example.module_common.util.NfcUtils;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.databinding.ActivityLocationBinding;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class LocationActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityLocationBinding> {

    private LocationListener locationListener;

    @Override
    protected ActivityLocationBinding getViewBinding() {
        return ActivityLocationBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("定位", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        locationListener = location -> mBinding.locationText.setText("经度："+location.getLongitude()+",纬度："+location.getLatitude());
    }

    @Override
    @SuppressLint("MissingPermission")
    protected void onResume() {
        super.onResume();
        ModuleUtils.startLocation(this, locationListener);
    }

    @Override
    protected void onDestroy() {
        ModuleUtils.stopLocation(locationListener);
        super.onDestroy();
    }
}