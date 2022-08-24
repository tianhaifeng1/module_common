package com.example.module_common.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.module_common.base.InitActivity;
import com.example.module_common.base.TView;

/**
 * Created by Administrator on 2017/10/20.
 */

public class TFragment extends Fragment implements TView {

    public InitActivity activity;
    public int mPageNum = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (InitActivity) getActivity();
    }

    /**
     * 用于初始化请求数据
     */
    public void initData(){

    }

    //-----------------------请求返回的方法--------------
    @Override
    public void tPostError(String errorMsg) {
        activity.tPostError(errorMsg);
    }

    @Override
    public void showDialog(String msg) {
        activity.showDialog(msg);
    }

    @Override
    public void hideDialog() {
        activity.hideDialog();
    }

    @Override
    public void tPostFail(int resultState) {
        activity.tPostFail(resultState);
    }

    @Override
    public void tRemind(String message) {
        activity.tRemind(message);
    }

    @Override
    public void tPostFinish(int code) {
        activity.tPostFinish(code);
    }


}
