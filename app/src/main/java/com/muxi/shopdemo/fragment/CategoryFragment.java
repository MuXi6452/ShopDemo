package com.muxi.shopdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.muxi.shopdemo.R;
import com.muxi.shopdemo.fragment.category.CategoryFragment0;
import com.muxi.shopdemo.fragment.category.CategoryFragment1;
import com.muxi.shopdemo.fragment.category.CategoryFragment2;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class CategoryFragment extends Fragment {
    private String[] mTabTxt = {"蔬菜水果", "肉蛋水产", "乳品烘培", "主食熟食", "速食冻品"};
    private VerticalTabLayout mVerticalTabLayout;

    public static CategoryFragment getInstance() {
        return new CategoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, null);
        mVerticalTabLayout = v.findViewById(R.id.verticalTabLayout);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout_category, CategoryFragment0.getInstance());
        ft.commit();
        for (int i = 0; i < mTabTxt.length; i++) {
            QTabView qTabView = new QTabView(getActivity()).setTitle(new ITabView.TabTitle.Builder()
                            .setContent(mTabTxt[i]).setTextColor(Color.rgb(255,69,0), Color.BLACK).build());
            mVerticalTabLayout.addTab(qTabView);
            qTabView.setPadding(30,30,30,30);
        }

        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                FragmentTransaction ft = fm.beginTransaction();
                switch (position){
                    case 0:
                        ft.replace(R.id.framelayout_category, CategoryFragment0.getInstance());
                        ft.commit();
                        break;
                    case 1:
                        ft.replace(R.id.framelayout_category, CategoryFragment1.getInstance());
                        ft.commit();
                        break;
                    case 2:
                        ft.replace(R.id.framelayout_category, CategoryFragment2.getInstance());
                        ft.commit();
                        break;
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

}
