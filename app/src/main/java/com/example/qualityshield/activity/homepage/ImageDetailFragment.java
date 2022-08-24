package com.example.qualityshield.activity.homepage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bm.library.PhotoView;
import com.example.module_common.mvp.TPresenter;
import com.example.module_common.official.OfficialMVPFragment;
import com.example.module_common.util.GlideUtile;
import com.example.qualityshield.R;
import com.example.qualityshield.databinding.ImageDetailFragmentBinding;

import java.util.ArrayList;

public class ImageDetailFragment extends OfficialMVPFragment {

    private String mImageUrl;
    private int mImageType;
//    private PhotoView mImageView;
    private ArrayList<String> mList = new ArrayList<>();
    private static final int SAVE_SUCCESS =1;

    private static final Handler HANDLER = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == SAVE_SUCCESS) {
                Log.i("SAVE_SUCCESS","1");
            }
        }
    };

    public static ImageDetailFragment newInstance(ArrayList<String> list, String imageUrl, int imageType) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        args.putInt("type", imageType);
        if (list != null && list.size() > 0) {
            args.putStringArrayList("list", list);
        }
        f.setArguments(args);
        return f;
    }

    public static ImageDetailFragment newInstance(String imageUrl, int imageType) {
        return newInstance(null, imageUrl, imageType);
    }

    private ImageDetailFragmentBinding mBinding;

    @Override
    public void bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mBinding = ImageDetailFragmentBinding.inflate(inflater);
    }

    @Override
    public View getLayoutId() {
        return mBinding.getRoot();
    }

    @Override
    protected void initFragmentView(View view) {
        if (getArguments() != null) {
            mImageUrl = getArguments().getString("url");
            mImageType = getArguments().getInt("type");
            mList = getArguments().getStringArrayList("list");
        }

//        mImageView = view.findViewById(R.id.image);
//        mImageView.setOnPhotoTapListener(new OnPhotoTapListener() {
//            @Override
//            public void onPhotoTap(ImageView view, float x, float y) {
//                Intent intent = new Intent();
//                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, mList);
//                if (null != activity) {
//                    activity.finish();
//                }
//            }
//        });

        mBinding.img.enable();

        GlideUtile.bindImageView(activity.context, mImageUrl, mBinding.img);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }
}
