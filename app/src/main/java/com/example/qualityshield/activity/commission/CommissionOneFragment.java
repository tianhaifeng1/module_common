package com.example.qualityshield.activity.commission;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPFragment;
import com.example.qualityshield.R;
import com.example.qualityshield.databinding.FragmentCommissionOneBinding;
import com.qmuiteam.qmui.layout.QMUILinearLayout;


public class CommissionOneFragment extends OfficialMVPFragment {

    private FragmentCommissionOneBinding mBinding;

    @Override
    public void bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mBinding = FragmentCommissionOneBinding.inflate(inflater);
    }

    @Override
    public View getLayoutId() {
        return mBinding.getRoot();
    }

    @Override
    protected void initFragmentView(View view) {
        mBinding.fmCommissionLin.setRadius(15);

        TextChange textChange = new TextChange();
        mBinding.showEdit.addTextChangedListener(textChange);
    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }

    //EditText的监听器
    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mBinding.showNum.setText(mBinding.showEdit.length() + "/200");
        }
    }
}
