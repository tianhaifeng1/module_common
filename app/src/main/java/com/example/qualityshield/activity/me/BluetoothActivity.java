package com.example.qualityshield.activity.me;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.databinding.ActivityBluetoothBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class BluetoothActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityBluetoothBinding> {

    @Override
    protected ActivityBluetoothBinding getViewBinding() {
        return ActivityBluetoothBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("蓝牙打印", true);
        titleModule.setTitleBottomLineShow(false);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.getTitleView().setVisibility(View.VISIBLE);
        titleModule.setTitleBackground(R.color.tab_checked);
        titleModule.initTitleMenu(2, "连接蓝牙");
        titleModule.setMenuTextColor(R.color.color_white);
        titleModule.setBackImage(R.mipmap.ic_back_white);

        mBinding.layoutDefaultBtn.setOnClickListener(v -> {
            initData();
        });

        initData();
    }

    private void initData() {
        if (getConnectedBtDevice() != null) {
            Log.i("thf", getConnectedBtDevice());
            mBinding.layoutDefaultAll.setVisibility(View.GONE);
            mBinding.bluetoothName.setText(getConnectedBtDevice());
        } else {
            Log.i("thf", "没有蓝牙");
            mBinding.layoutDefaultAll.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickRightText(View view) {
        super.onClickRightText(view);
        skipActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS), 100);//直接进入手机中的蓝牙设置界面
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            initData();
        }
    }

    private String getConnectedBtDevice() {
        //获取蓝牙适配器
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //得到已匹配的蓝牙设备列表
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            for (BluetoothDevice bondedDevice : bondedDevices) {
                try {
                    //使用反射调用被隐藏的方法
                    Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
                    isConnectedMethod.setAccessible(true);
                    boolean isConnected = (boolean) isConnectedMethod.invoke(bondedDevice, (Object[]) null);
                    Log.i("123", "isConnected：" + isConnected);
                    if (isConnected) {
                        return bondedDevice.getName();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}