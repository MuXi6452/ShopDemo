package com.muxi.shopdemo.util;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showToast(Context context,String txt){
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
    }
}
