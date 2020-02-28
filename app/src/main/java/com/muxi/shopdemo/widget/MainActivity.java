package com.muxi.shopdemo.widget;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.fragment.CartFragment;
import com.muxi.shopdemo.fragment.CategoryFragment;
import com.muxi.shopdemo.fragment.HomeFragment;
import com.muxi.shopdemo.fragment.HotFragment;
import com.muxi.shopdemo.fragment.MyFragment;
public class MainActivity extends AppCompatActivity {
    private TabLayout bottomNav;
    private String[] bottomNavTabsTxt = {"主页", "热点", "分类", "购物车", "我的"};
    private int[] bottomNavTabsIcon = {R.drawable.selector_bottom_nav_icon_home, R.drawable.selector_bottom_nav_icon_hot, R.drawable.selector_bottom_nav_icon_category
            , R.drawable.selector_bottom_nav_icon_cart, R.drawable.selector_bottom_nav_icon_my};
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    private void initData() {
        bottomNav = findViewById(R.id.bottom_nav);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initBottomNavAndMainFraments();
    }
    //初始化底部导航栏和主要的5个fragment
    private void initBottomNavAndMainFraments() {
        //设置底部导航栏样式
        for (int i = 0; i < bottomNavTabsTxt.length; i++) {
            bottomNav.addTab(bottomNav.newTab().setCustomView(getBottomNavTabs(i)));
        }
        changeFragments(HomeFragment.getInstance(),"HomeFragment");
        bottomNav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        changeFragments(HomeFragment.getInstance(),"HomeFragment");
                        break;
                    case 1:
                        changeFragments(HotFragment.getInstance(),"HotFragment");
                        break;
                    case 2:
                        changeFragments(CategoryFragment.getInstance(),"CategoryFragment");
                        break;
                    case 3:
                        changeFragments(CartFragment.getInstance(),"CartFragment");
                        break;
                    case 4:
                        changeFragments(MyFragment.getInstance(),"MyFragment");
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
    private void changeFragments(Fragment fragment,String tag) {
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout_home, fragment,tag);
        ft.addToBackStack(tag);
        ft.commit();
    }
    public View getBottomNavTabs(final int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.nav_bottom, null);
        ImageView iv_bottom_tabs = v.findViewById(R.id.iv_bottom_tabs);
        TextView tv_bottom_tabs = v.findViewById(R.id.tv_bottom_tabs);
        iv_bottom_tabs.setImageResource(bottomNavTabsIcon[position]);
        tv_bottom_tabs.setText(bottomNavTabsTxt[position]);
        return v;
    }
    @Override
    public void onBackPressed() {
        if(fm!=null&&fm.getBackStackEntryCount()>1){
            bottomNav.setVisibility(View.VISIBLE);
            fm.popBackStack();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("真的要退出吗");
            builder.setPositiveButton("残忍退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("再看看", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            builder.create().show();
        }
    }
//    private boolean isFirst = true;
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if(hasFocus){
//            if (isFirst) {
//                isFirst = false;
//                showPopu();
//            }
//        }
//    }
//
//    public void showPopu() {
//        View popuView = View.inflate(this, R.tab_my_order_item.popu_home_item, null);
//        PopupWindow popupWindow = new PopupWindow(popuView, 500, 500);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//    }
}
