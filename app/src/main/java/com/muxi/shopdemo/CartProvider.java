package com.muxi.shopdemo;

import android.content.Context;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.muxi.shopdemo.bean.CartGoodsBean;
import com.muxi.shopdemo.util.PreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class CartProvider {
    private SparseArray<CartGoodsBean> datas = null;    //存储购物车数据
    private Context mCtx;
    private String SP_KEY = "key";
    private Gson gson;

    public CartProvider(Context context) {
        datas = new SparseArray<>();
        this.mCtx = context;
        gson = new Gson();
        list2Sparse();
    }

    public void put(CartGoodsBean bean) {
        CartGoodsBean temp = datas.get(Integer.parseInt(bean.getGoodsId()));
        if (temp != null) {
            temp.count++;
        } else {
            temp = bean;
            temp.count = 1;
        }
        datas.put(Integer.parseInt(bean.getGoodsId()), temp);
        commit();
    }

    public void sub(CartGoodsBean bean) {
        int id = Integer.parseInt(bean.getGoodsId());
        CartGoodsBean temp = datas.get(id);
        if (temp != null && temp.count > 2) {
            temp.count--;
        } else {
            temp = bean;
            temp.count = 1;
        }
        datas.put(id, temp);
        commit();
    }

    public void update(CartGoodsBean bean) {
        int id = Integer.parseInt(bean.getGoodsId());
        datas.put(id, bean);
        commit();
    }

    public void delete(CartGoodsBean bean) {
        datas.remove(Integer.parseInt(bean.getGoodsId()));
        commit();
    }

    public List<CartGoodsBean> getAll() {
        return getDataFromLocal();
    }

    //保存数据到本地sharepreferences中
    public void commit() {
        List<CartGoodsBean> list = sparse2List();
        PreferencesUtil.putString(mCtx, SP_KEY, gson.toJson(list));
    }

    private List sparse2List() {
        int size = datas.size();
        List<CartGoodsBean> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(datas.valueAt(i));
        }
        return list;
    }

    public void list2Sparse() {
        List<CartGoodsBean> localData = getDataFromLocal();
        if (localData != null && localData.size() > 0) {
            for (CartGoodsBean b : localData) {
                datas.put(Integer.parseInt(b.getGoodsId()), b);
            }
        }
    }

    //从本地SharePreferences取数据
    public List<CartGoodsBean> getDataFromLocal() {
        String json = PreferencesUtil.getString(mCtx, SP_KEY);
        List<CartGoodsBean> beans = null;
        if (json != null) {
            beans = gson.fromJson(json, new TypeToken<List<CartGoodsBean>>() {}.getType());//解析
            return beans;
        }
        return null;
    }
}
