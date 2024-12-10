package com.example.module_common.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.R;
import com.example.module_common.model.LocationModel;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.huawei.hms.hmsscankit.ScanUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @ProjectName: QualityShield
 * @Package: com.example.qualityshield.assist
 * @ClassName: ModuleUtils
 * @Description:
 * @Author: tian
 * @CreateDate: 2022/7/26 13:46
 */
public class ModuleUtils {

    /**
     * 打开扫码页面，返回值通过传入的响应码在onActivityResult获取
     *
     * @param activity    activity
     * @param requestCode 响应码
     */
    public static void startScan(Activity activity, int requestCode) {
        String[] requestAll = {
                Manifest.permission.CAMERA
        };
        XXPermissions.with(activity)
                .permission(requestAll)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                        if (allGranted) {
                            ScanUtil.startScan(activity, requestCode, null);
                        }
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {
                        if (doNotAskAgain) {
                            ToastUtils.showShort("请选择'" + activity.getResources().getString(R.string.app_name) + "'程序，点击进行手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(activity);
                        } else {
                            ToastUtils.showShort("禁止权限有可能影响APP正常运行");
                        }
                    }
                });
    }

    /**
     * 进行打电话意图
     *
     * @param context  上下文
     * @param phoneNum 手机号码
     */
    public static void callPhone(Context context, String phoneNum) {
        try {
            //我们需要告诉系统，我们的动作：我要打电话
            //创建意图对象
            Intent intent = new Intent();
            //  直接打电话的动作
            //intent.setAction(Intent.ACTION_CALL);
            //  跳转到拨号界面
            intent.setAction(Intent.ACTION_DIAL);
            //设置打给谁
            intent.setData(Uri.parse("tel:" + phoneNum));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”
            //把动作告诉系统,启动系统打电话功能。
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showShort("电话意图未能成功打开");
        }
    }

    private static String provider = "";
    private static LocationManager alm;

    /**
     * 启用定位功能
     *
     * @param activity         activity
     * @param locationListener 定位监听
     */
    @SuppressLint("MissingPermission")
    public static void startLocation(Activity activity, LocationListener locationListener) {


        String[] requestAll = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET
        };

        XXPermissions.with(activity)
                .permission(requestAll)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                        if (allGranted) {
                            alm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
                            List<String> providerList = alm.getProviders(true);
                            if (providerList.contains(LocationManager.NETWORK_PROVIDER)) { //网络提供器
                                provider = LocationManager.NETWORK_PROVIDER;
                            } else if (provider.contains(LocationManager.GPS_PROVIDER)) { //GPS提供器
                                provider = LocationManager.GPS_PROVIDER;
                            } else {
                                ToastUtils.showShort("请开启GPS");
                                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                                activity.startActivityForResult(intent, 0); //此为设置完成后返回到获取界面
                                return;
                            }

                            Location location = alm.getLastKnownLocation(provider); // 通过GPS获取位置
                            EventBus.getDefault().post(new LocationModel(location));
                            if (locationListener != null) {
                                // 设置监听*器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
                                ToastUtils.showShort("位置监听已开启");
                                alm.requestLocationUpdates(provider, 20 * 1000, 0, locationListener);
                            }
                        }
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {
                        if (doNotAskAgain) {
                            ToastUtils.showShort("请选择'" + activity.getResources().getString(R.string.app_name) + "'程序，点击进行手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(activity);
                        } else {
                            ToastUtils.showShort("禁止权限有可能影响APP正常运行");
                        }
                    }
                });
    }

    /**
     * 关闭定位功能
     *
     * @param locationListener locationListener
     */
    public static void stopLocation(LocationListener locationListener) {
        alm.removeUpdates(locationListener);
    }
}
