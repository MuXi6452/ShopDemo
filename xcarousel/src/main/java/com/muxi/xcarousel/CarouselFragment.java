package com.muxi.xcarousel;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.io.Serializable;
public class CarouselFragment extends Fragment {
    private ViewPager mBannerViewPager;
    private TabLayout mBannerTabLayout;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1001:
                    mBannerViewPager.setCurrentItem(mBannerViewPager.getCurrentItem() + 1, true);
                    break;
                case 1002:
                    mBannerViewPager.setCurrentItem(0, true);
                    break;
            }
        }
    };
    public static CarouselFragment getInstance(String[] bannerPicUrls) {
        CarouselFragment f = new CarouselFragment();
        Bundle b = new Bundle();
        b.putSerializable("bannerPicUrls",(Serializable) bannerPicUrls);
        f.setArguments(b);
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.carousel_layout, null);
        mBannerViewPager = v.findViewById(R.id.banner_viewpager);
        mBannerTabLayout = v.findViewById(R.id.banner_tablayout);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadBanner();
    }

    private void loadBanner() {//轮播图
        final String[] bannerPicUrls = (String[])getArguments().getSerializable("bannerPicUrls");
        mBannerViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                CarouselItemFragment f = CarouselItemFragment.getInstance();
                Bundle b = new Bundle();
                b.putSerializable("bannerPicUrls",(Serializable) bannerPicUrls);
                f.setArguments(b);
                return f;
            }
            @Override
            public int getCount() {
                return bannerPicUrls.length;
            }
        });
        mBannerTabLayout.setupWithViewPager(mBannerViewPager);
        for (int i = 0; i < bannerPicUrls.length; i++) {
            mBannerTabLayout.getTabAt(i).setCustomView(getBannerTabView());
        }
        mBannerTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mHandler.sendEmptyMessageDelayed(1001, 3000);
                if (tab.getPosition() == bannerPicUrls.length - 1) {
                    mHandler.sendEmptyMessageDelayed(1002, 3000);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mHandler.sendEmptyMessageDelayed(1001, 3000);
    }
    public View getBannerTabView() {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.carousel_dot, null);
        return v;
    }

}
