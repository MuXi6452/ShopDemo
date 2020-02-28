package com.muxi.shopdemo.fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.muxi.shopdemo.fragment.my.MyOrderFragment;
import com.muxi.shopdemo.widget.CircleImageDrawable;
import com.muxi.shopdemo.widget.MainActivity;
import com.muxi.shopdemo.R;

import java.util.ArrayList;
import java.util.List;
public class MyFragment extends Fragment {
    private ImageView iv_my_setting;
    private DrawerLayout mDrawerlayout;
    private ImageView mIvMyProfile;
    private FrameLayout mFramelayoutMy;
    private ListView mListviewMy;
    private FragmentManager mFragmentManager;

    public static MyFragment getInstance() {
        return new MyFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, null);
        mDrawerlayout = v.findViewById(R.id.drawerlayout);
        iv_my_setting = v.findViewById(R.id.iv_my_setting);
        mIvMyProfile = v.findViewById(R.id.iv_my_profile);
        mFramelayoutMy = v.findViewById(R.id.framelayout_my);
        mListviewMy = v.findViewById(R.id.listview_my);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p1);
        CircleImageDrawable c = new CircleImageDrawable(bitmap);
        mIvMyProfile.setImageDrawable(c);
        iv_my_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerlayout.openDrawer(Gravity.RIGHT);
            }
        });
        mFragmentManager = getActivity().getSupportFragmentManager();
        List<String> dataList = new ArrayList<>();
        dataList.add("我的订单");
        dataList.add("我的收藏");
        dataList.add("我的收货地址");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
        mListviewMy.setAdapter(adapter);
        mListviewMy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: //跳到我的订单
                        changeFragment(MyOrderFragment.getInstance(),"MyOrderFragment");
                        break;
                }
            }
        });
        mIvMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(ChangeProfileFragment.getInstance(),"ChangeProfileFragment");
            }
        });
    }

    private void changeFragment(Fragment fragment,String tag) {
        mFramelayoutMy.setVisibility(View.VISIBLE);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.framelayout_my, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
        if (getActivity() instanceof MainActivity) {
            MainActivity a = (MainActivity) getActivity();
            TabLayout b = a.findViewById(R.id.bottom_nav);
            b.setVisibility(View.GONE);
        }
    }
}
