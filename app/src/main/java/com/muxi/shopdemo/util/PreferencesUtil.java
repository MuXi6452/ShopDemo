package com.muxi.shopdemo.util;
import android.content.Context;
import android.content.SharedPreferences;
public class PreferencesUtil {

    public static void putString(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences("xSp", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();
    }

    public static String getString(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("xSp", Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }
}
