package com.muxi.shopdemo.fragment.my;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;
import com.muxi.shopdemo.R;
public class MyOrderFragment extends Fragment {
    TabLayout tablayoutMyOrder;
    private String[] tabTxts = {"已支付订单","未支付订单"};
    public static MyOrderFragment getInstance() {
        return new MyOrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_order, null);
        tablayoutMyOrder = v.findViewById(R.id.tablayout_my_order);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < tabTxts.length; i++) {
            tablayoutMyOrder.addTab(tablayoutMyOrder.newTab().setCustomView(getMyOrderItemTabs(i)));
        }
        changeFragment(MyOrderItemFragment.getInstance(),"MyOrderItemFragment");
        tablayoutMyOrder.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        changeFragment(MyOrderItemFragment.getInstance(),"MyOrderItemFragment");
                        break;
                    case 1:
                        changeFragment(MyOrderItemFragment.getInstance(),"MyOrderItemFragment");
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public View getMyOrderItemTabs(int position) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tab_my_order_item, null);
        TextView tv_tab_my_order_item = v.findViewById(R.id.tv_tab_my_order_item);
        tv_tab_my_order_item.setText(tabTxts[position]);
        return v;
    }
    private void changeFragment(Fragment fragment,String tag) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout_my_order, fragment, tag);
        ft.commit();
    }
}
