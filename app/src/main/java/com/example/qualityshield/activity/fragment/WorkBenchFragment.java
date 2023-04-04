package com.example.qualityshield.activity.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPFragment;
import com.example.module_common.util.GlideUtile;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.LoginActivity;
import com.example.qualityshield.activity.MainActivity;
import com.example.qualityshield.activity.MainNavActivity;
import com.example.qualityshield.activity.adapter.MenuAdapter;
import com.example.qualityshield.activity.homepage.LocationActivity;
import com.example.qualityshield.activity.homepage.NFCActivity;
import com.example.qualityshield.activity.homepage.WebActivity;
import com.example.qualityshield.activity.photo.DemoActivity;
import com.example.qualityshield.activity.homepage.QualityControlActivity;
import com.example.qualityshield.activity.homepage.QualityTestingActivity;
import com.example.qualityshield.activity.homepage.RollingActivity;
import com.example.qualityshield.activity.homepage.TaskBoardActivity;
import com.example.qualityshield.activity.homepage.TransferListActivity;
import com.example.qualityshield.activity.homepage.WorkSheetActivity;
import com.example.qualityshield.activity.me.BluetoothActivity;
import com.example.qualityshield.activity.message.MessageActivity;
import com.example.qualityshield.assist.DemoConstant;
import com.example.qualityshield.assist.DemoUtils;
import com.example.qualityshield.assist.MacUtil;
import com.example.module_common.util.ModuleUtils;
import com.example.qualityshield.assist.RecycleViewDivider;
import com.example.qualityshield.bean.MenuBean;
import com.example.qualityshield.databinding.FragmentWorkBenchBinding;
import com.example.qualityshield.dialog.RemindDialog;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: QualityShield
 * @Package: com.example.qualityshield.activity.fragment
 * @ClassName: WorkBenchFragment
 * @Description:
 * @Author: tian
 * @CreateDate: 2022/7/26 9:43
 */
public class WorkBenchFragment extends OfficialMVPFragment implements View.OnClickListener {

    //首页模块数组
    private List<MenuBean> menuBeans1 = new ArrayList<>();
    private List<MenuBean> menuBeans2 = new ArrayList<>();
    private List<MenuBean> menuBeans3 = new ArrayList<>();

    private MainNavActivity navActivity;

    private FragmentWorkBenchBinding mBinding;

    @Override
    public void bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mBinding = FragmentWorkBenchBinding.inflate(inflater);
    }

    @Override
    public View getLayoutId() {
        return mBinding.getRoot();
    }

    @Override
    protected void initFragmentView(View view) {

        navActivity = (MainNavActivity) getActivity();

        //获取资源文件路径
        String[] stringarray1 = getResources().getStringArray(R.array.product_name);
        TypedArray item1 = getResources().obtainTypedArray(R.array.product_icon);
        for (int i = 0; i < stringarray1.length; i++) {
            MenuBean bean = new MenuBean(stringarray1[i], item1.getResourceId(i, 0));
            menuBeans1.add(bean);
        }

        String[] stringarray2 = getResources().getStringArray(R.array.checkout_name);
        TypedArray item2 = getResources().obtainTypedArray(R.array.checkout_icon);
        for (int i = 0; i < stringarray2.length; i++) {
            MenuBean bean = new MenuBean(stringarray2[i], item2.getResourceId(i, 0));
            menuBeans2.add(bean);
        }

        String[] stringarray3 = getResources().getStringArray(R.array.warehouse_name);
        TypedArray item3 = getResources().obtainTypedArray(R.array.warehouse_icon);
        for (int i = 0; i < stringarray3.length; i++) {
            MenuBean bean = new MenuBean(stringarray3[i], item3.getResourceId(i, 0));
            menuBeans3.add(bean);
        }

        mBinding.titleMenu2.setOnClickListener(this);
        mBinding.titleMenu3.setOnClickListener(this);

        //初始化banner组件
        initBanner();
        //初始化适配器
        initAdapter();
    }

    private MenuAdapter menuAdapter1;
    private MenuAdapter menuAdapter2;
    private MenuAdapter menuAdapter3;

    private void initAdapter() {
        mBinding.fmOneProductionRv.setLayoutManager(new GridLayoutManager(activity.context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mBinding.fmOneProductionRv.addItemDecoration(new RecycleViewDivider(
                activity.context, GridLayoutManager.VERTICAL, 2, getResources().getColor(R.color.color_line)));
        mBinding.fmOneProductionRv.addItemDecoration(new RecycleViewDivider(
                activity.context, GridLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.color_line)));
        menuAdapter1 = new MenuAdapter(menuBeans1);
        menuAdapter1.setOnItemClickListener((adapter, view, position) -> {
            MenuBean menuBean = (MenuBean) adapter.getData().get(position);
            if (menuBean.getMenuName().equals("上机")) {
                //处理上机业务逻辑
            } else if (menuBean.getMenuName().equals("下机")) {
                RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                        .setMessage("是否确认退出登录？")
                        .setAffirmText("确认")
                        .setCancleText("取消")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //确认退出登录
                                activity.skipActivity(LoginActivity.class);
                                navActivity.finish();
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog.show(getFragmentManager(), "dialog_edit_remind");
            } else if (menuBean.getMenuName().equals("收卡")) {
                if (DemoConstant.type == 0) {
                    Intent intent = new Intent(activity, TaskBoardActivity.class);
                    activity.skipActivity(intent);
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                            .setMessage("暂未有可领卡的工序")
                            .setAffirmText("")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .create();
                    remindDialog.show(getFragmentManager(), "dialog_edit_remind");
                }
            } else if (menuBean.getMenuName().equals("报工")) {
                if (DemoConstant.type == 1) {
                    Intent intent = new Intent(activity, RollingActivity.class);
                    intent.putExtra("type", 2);
                    activity.skipActivity(intent);
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                            .setMessage("暂未有可报工的工序")
                            .setAffirmText("")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .create();
                    remindDialog.show(getFragmentManager(), "dialog_edit_remind");
                }
            } else if (menuBean.getMenuName().equals("质检")) {
                if (DemoConstant.type == 2) {
                    Intent intent = new Intent(activity, QualityTestingActivity.class);
                    intent.putExtra("type", 1);
                    activity.skipActivity(intent);
                } else {
                    RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                            .setMessage("暂未有待质检的工序")
                            .setAffirmText("")
                            .setCancleText("取消")
                            .setCancelable(true)
                            .create();
                    remindDialog.show(getFragmentManager(), "dialog_edit_remind");
                }
            } else if (menuBean.getMenuName().equals("历史检验")) {
                Intent intent = new Intent(activity, QualityTestingActivity.class);
                intent.putExtra("type", 2);
                activity.skipActivity(intent);
            } else if (menuBean.getMenuName().equals("工艺图纸")) {
                Intent intent = new Intent(activity, WorkSheetActivity.class);
                activity.skipActivity(intent);
            } else if (menuBean.getMenuName().equals("品质看板")) {
                Intent intent = new Intent(activity, QualityControlActivity.class);
                activity.skipActivity(intent);
            } else if (menuBean.getMenuName().equals("转运")) {
                Intent intent = new Intent(activity, TransferListActivity.class);
                activity.skipActivity(intent);
            } else if (menuBean.getMenuName().equals("wifi")) {
                String wifi = "";
                if (NetworkUtils.isWifiConnected()) {
                    wifi = "WIFI已连接";
                    String mac = MacUtil.getMac(activity);
                    Log.i("mac====", mac);
                } else {
                    wifi = "WIFI未连接";
                    handler.sendEmptyMessageDelayed(0,2000);
                }
                ToastUtils.showShort(wifi);
            } else if(menuBean.getMenuName().equals("蓝牙")) {
                //蓝牙连接
                activity.skipActivity(BluetoothActivity.class);
            } else if(menuBean.getMenuName().equals("拍照")) {
                //图片选择
                activity.skipActivity(DemoActivity.class);
            } else if(menuBean.getMenuName().equals("FNC")) {
                //图片选择
                activity.skipActivity(NFCActivity.class);
            } else if(menuBean.getMenuName().equals("定位")) {
                //定位
                activity.skipActivity(LocationActivity.class);
            } else if(menuBean.getMenuName().equals("外部链接")) {
                //web
                activity.skipActivity(WebActivity.class);
            }
        });
        mBinding.fmOneProductionRv.setAdapter(menuAdapter1);

        mBinding.fmOneCheckoutRv.setLayoutManager(new GridLayoutManager(activity.context, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        menuAdapter2 = new MenuAdapter(menuBeans2);
        menuAdapter2.setOnItemClickListener((adapter, view, position) -> {
            MenuBean menuBean = (MenuBean) adapter.getData().get(position);
            if (menuBean.getMenuName().equals("质检")) {
            }
        });
        mBinding.fmOneCheckoutRv.setAdapter(menuAdapter2);

        mBinding.fmOneWarehouseRv.setLayoutManager(new GridLayoutManager(activity.context, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        menuAdapter3 = new MenuAdapter(menuBeans3);
        menuAdapter3.setOnItemClickListener((adapter, view, position) -> {
            MenuBean menuBean = (MenuBean) adapter.getData().get(position);
            if (menuBean.getMenuName().equals("入库")) {

            }
        });
        mBinding.fmOneWarehouseRv.setAdapter(menuAdapter3);
    }

    //handler中实现延迟退出，两次返回才能退出
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void initBanner() {
        mBinding.fmOneBanner.setAdapter(new ImageAdapter(DemoUtils.getList()))
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setBannerRound(15)
                .setIndicator(new CircleIndicator(activity.context));
    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_menu2:
                ModuleUtils.startScan(activity, 100);
                break;
            case R.id.title_menu3:
                activity.skipActivity(MessageActivity.class);
                break;
        }
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
