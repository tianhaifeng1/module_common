package com.example.qualityshield.activity.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.module_common.official.OfficialMVPActivity;
import com.example.qualityshield.R;
import com.example.qualityshield.activity.HomePresenter;
import com.example.qualityshield.activity.HomeView;
import com.example.qualityshield.databinding.ActivityImagePagerBinding;

import java.util.ArrayList;


public class ImagePagerActivity extends OfficialMVPActivity<HomeView, HomePresenter, ActivityImagePagerBinding> implements ViewPager.OnPageChangeListener {

    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    /**
     * 1网络图片，2本地图片
     */
    public static final String EXTRA_IMAGE_TYPE = "image_type";

    private int pagerPosition;
    private int imageType;
    ImagePagerAdapter mAdapter;
    ArrayList<String> mUrls = new ArrayList<>();
    private final String TAG = "ImagePagerActivity";
    private int index;

    private String imageUrl;

    @Override
    protected ActivityImagePagerBinding getViewBinding() {
        return ActivityImagePagerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
//        mIndicatorBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DonwloadSaveImg.donwloadImg(context,imageUrl);//iPath
//            }
//        });
        mBinding.titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
//        imageType = getIntent().getIntExtra(EXTRA_IMAGE_TYPE, 0);
        mUrls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager());
        mAdapter.refreshView(mUrls);
        mBinding.pager.setAdapter(mAdapter);
//        CharSequence text = getString(mPager.getAdapter().getCount());
//        mIndicator.setText(text);
        if (null != mUrls && mUrls.size() > 0) {
            imageUrl = mUrls.get(0);
        }
        // 更新下标
        mBinding.pager.addOnPageChangeListener(this);
        mBinding.pager.setCurrentItem(pagerPosition);
        int photoPosition = pagerPosition + 1;
        mBinding.indicator.setText(photoPosition + "/" + mUrls.size());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        index = position;
        int photoPosition = position + 1;
        mBinding.indicator.setText(photoPosition + "/" + mUrls.size());
        imageUrl = mUrls.get(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void refreshView(ArrayList<String> fileList) {
            this.fileList = fileList;
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
//			return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
//            imageUrl = url;
            int type = imageType;
            return ImageDetailFragment.newInstance(fileList, url, type);
        }
    }

    @Override
    protected void onDestroy() {
        if (mBinding.pager != null) {
            mBinding.pager.removeOnPageChangeListener(this);
        }
        super.onDestroy();
    }
}