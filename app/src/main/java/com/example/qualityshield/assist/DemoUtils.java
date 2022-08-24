package com.example.qualityshield.assist;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.qualityshield.R;
import com.example.qualityshield.bean.ProcessListBean;
import com.example.qualityshield.bean.TechnologyBean;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.huawei.hms.hmsscankit.ScanUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemoUtils {

    /**
     * 填充数据
     *
     * @return
     */
    public static List<String> getList() {
        List<String> info = new ArrayList<>();
        info.add("1");
        info.add("2");
        info.add("3");
        info.add("4");

        return info;
    }

    //获取假数据
    public static List<BaseNode> getEntity() {
        //总的 list，里面放的是 FirstNode
        List<BaseNode> list = new ArrayList<>();
        //SecondNode 的 list
        List<BaseNode> secondNodeList = new ArrayList<>();
        ProcessListBean.ProcessChildBean seNode11 = new ProcessListBean.ProcessChildBean("工序类型", "操作检验工序");
        secondNodeList.add(seNode11);
        ProcessListBean.ProcessChildBean seNode12 = new ProcessListBean.ProcessChildBean("所属工段", "蜡膜");
        secondNodeList.add(seNode12);
        ProcessListBean.ProcessChildBean seNode13 = new ProcessListBean.ProcessChildBean("是否关键工序", "否");
        secondNodeList.add(seNode13);
        ProcessListBean.ProcessChildBean seNode14 = new ProcessListBean.ProcessChildBean("当前流程", "未开始");
        secondNodeList.add(seNode14);
        ProcessListBean.ProcessChildBean seNode15 = new ProcessListBean.ProcessChildBean("备注", "");
        secondNodeList.add(seNode15);
        ProcessListBean entity1 = new ProcessListBean();
        entity1.setName("压制蜡模");
        entity1.setProcessChildBeans(secondNodeList);
        entity1.setExpanded(false);
        list.add(entity1);

        //SecondNode 的 list
        List<BaseNode> secondNodeList2 = new ArrayList<>();
        ProcessListBean.ProcessChildBean seNode21 = new ProcessListBean.ProcessChildBean("工序类型", "检验工序");
        secondNodeList2.add(seNode21);
        ProcessListBean.ProcessChildBean seNode22 = new ProcessListBean.ProcessChildBean("所属工段", "蜡膜");
        secondNodeList2.add(seNode22);
        ProcessListBean.ProcessChildBean seNode23 = new ProcessListBean.ProcessChildBean("是否关键工序", "否");
        secondNodeList2.add(seNode23);
        ProcessListBean.ProcessChildBean seNode24 = new ProcessListBean.ProcessChildBean("当前流程", "未开始");
        secondNodeList2.add(seNode24);
        ProcessListBean.ProcessChildBean seNode25 = new ProcessListBean.ProcessChildBean("备注", "");
        secondNodeList2.add(seNode25);
        ProcessListBean entit2 = new ProcessListBean();
        entit2.setName("蜡膜尺检");
        entit2.setProcessChildBeans(secondNodeList2);
        entit2.setExpanded(false);
        list.add(entit2);

        //SecondNode 的 list
        List<BaseNode> secondNodeList3 = new ArrayList<>();
        ProcessListBean.ProcessChildBean seNode31 = new ProcessListBean.ProcessChildBean("工序类型", "操作检验工序");
        secondNodeList3.add(seNode31);
        ProcessListBean.ProcessChildBean seNode32 = new ProcessListBean.ProcessChildBean("所属工段", "蜡膜");
        secondNodeList3.add(seNode32);
        ProcessListBean.ProcessChildBean seNode33 = new ProcessListBean.ProcessChildBean("是否关键工序", "否");
        secondNodeList3.add(seNode33);
        ProcessListBean.ProcessChildBean seNode34 = new ProcessListBean.ProcessChildBean("当前流程", "未开始");
        secondNodeList3.add(seNode34);
        ProcessListBean.ProcessChildBean seNode35 = new ProcessListBean.ProcessChildBean("备注", "");
        secondNodeList3.add(seNode35);
        ProcessListBean entit3 = new ProcessListBean();
        entit3.setName("蜡膜组焊");
        entit3.setProcessChildBeans(secondNodeList3);
        entit3.setExpanded(false);
        list.add(entit3);

        //SecondNode 的 list
        List<BaseNode> secondNodeList4 = new ArrayList<>();
        ProcessListBean.ProcessChildBean seNode41 = new ProcessListBean.ProcessChildBean("工序类型", "操作检验工序");
        secondNodeList4.add(seNode41);
        ProcessListBean.ProcessChildBean seNode42 = new ProcessListBean.ProcessChildBean("所属工段", "制壳");
        secondNodeList4.add(seNode42);
        ProcessListBean.ProcessChildBean seNode43 = new ProcessListBean.ProcessChildBean("是否关键工序", "否");
        secondNodeList4.add(seNode43);
        ProcessListBean.ProcessChildBean seNode44 = new ProcessListBean.ProcessChildBean("当前流程", "未开始");
        secondNodeList4.add(seNode44);
        ProcessListBean.ProcessChildBean seNode45 = new ProcessListBean.ProcessChildBean("备注", "");
        secondNodeList4.add(seNode45);
        ProcessListBean entit4 = new ProcessListBean();
        entit4.setName("面层");
        entit4.setProcessChildBeans(secondNodeList4);
        entit4.setExpanded(false);
        list.add(entit4);

        //SecondNode 的 list
        List<BaseNode> secondNodeList5 = new ArrayList<>();
        ProcessListBean.ProcessChildBean seNode51 = new ProcessListBean.ProcessChildBean("工序类型", "操作工序");
        secondNodeList5.add(seNode51);
        ProcessListBean.ProcessChildBean seNode52 = new ProcessListBean.ProcessChildBean("所属工段", "制壳");
        secondNodeList5.add(seNode52);
        ProcessListBean.ProcessChildBean seNode53 = new ProcessListBean.ProcessChildBean("是否关键工序", "是");
        secondNodeList5.add(seNode53);
        ProcessListBean.ProcessChildBean seNode54 = new ProcessListBean.ProcessChildBean("当前流程", "未开始");
        secondNodeList5.add(seNode54);
        ProcessListBean.ProcessChildBean seNode55 = new ProcessListBean.ProcessChildBean("备注", "");
        secondNodeList5.add(seNode55);
        ProcessListBean entit5 = new ProcessListBean();
        entit5.setName("加固");
        entit5.setProcessChildBeans(secondNodeList5);
        entit5.setExpanded(false);
        list.add(entit5);
        return list;
    }

    //获取假数据
    public static List<TechnologyBean> getTechnologyBeans() {
        List<TechnologyBean> technologyBeans = new ArrayList<>();
        technologyBeans.add(new TechnologyBean("参数名称", "参数值", "备注"));
        technologyBeans.add(new TechnologyBean("热等静压压力", "100MPa~140MPa", ""));
        technologyBeans.add(new TechnologyBean("热等静压温度", "（910~930）℃", "仪表设定值为920℃。"));
        technologyBeans.add(new TechnologyBean("热等静压时间", "2h", "仪表设定值为2h。"));
        technologyBeans.add(new TechnologyBean("热等静压冷却方式", "随炉冷却", ""));
        technologyBeans.add(new TechnologyBean("热等静压出炉温度", "＜300℃", ""));
        technologyBeans.add(new TechnologyBean("退火真空度", "＜1Pa", ""));
        technologyBeans.add(new TechnologyBean("退火温度", "（725~735）℃", "仪表设定值为730℃。"));
        technologyBeans.add(new TechnologyBean("退火保温时间", "2h", "仪表设定值为2h。"));
        technologyBeans.add(new TechnologyBean("退火冷却方式", "炉冷", ""));
        technologyBeans.add(new TechnologyBean("退火出炉温度", "＜300℃", ""));
        return technologyBeans;
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 小数点前后大小不一致
     *
     * @param value
     * @return
     */
    public static SpannableString changTVsize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.7f), value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 时间戳转字符串
     *
     * @return
     */
    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "MM月dd日 HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time * 1000));
    }

    /**
     * 字符串转时间戳
     *
     * @return
     */
    public static long date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     * 将秒数转为时分秒
     * */
    public static String change(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }

        String th = h < 10 ? "0" + h : h + "";
        String td = d < 10 ? "0" + d : d + "";
        String ts = s < 10 ? "0" + s : s + "";

        return th + ":" + td + ":" + ts + "";
    }

    /*
     * 将秒数转为时分秒
     * */
    public static String change2(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }

        String td = d < 10 ? "0" + d : d + "";
        String ts = s < 10 ? "0" + s : s + "";

        if (h == 0) {
            if (d == 0) {
                return s + "秒";
            } else {
                return d + "分" + ts + "秒";
            }
        } else {
            return h + ":" + td + ":" + ts + "";
        }
    }

    public static String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

//    public static void bindImageViewC(Context context, Object path, int radius, ImageView imageView){
//        Glide.with(context)
//                .load(path)
//                .transform(new CenterCrop(),new GlideRoundTransform(radius))
//                .transition(withCrossFade())
//                .into((ImageView) imageView);
//    }

    public static String ToDouble(double d) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("0.00");
        return df.format(d);
    }

    /**
     * 删除单个文件
     *
     * @param filePathName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteSingleFile(String filePathName) {
        File file = new File(filePathName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                LogUtils.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + filePathName + "成功！");
                return true;
            } else {
                LogUtils.e("删除单个文件" + filePathName + "失败！");
                return false;
            }
        } else {
            LogUtils.e("删除单个文件失败：" + filePathName + "不存在！");
            return false;
        }
    }
}
