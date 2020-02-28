package com.muxi.shopdemo.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muxi.shopdemo.widget.CalculateActivity;
import com.muxi.shopdemo.CartProvider;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.bean.CartGoodsBean;
import com.muxi.shopdemo.rv.CartRvAdapter;
import com.muxi.shopdemo.rv.XOnCheckedChangeListener;
import com.muxi.shopdemo.rv.XOnItemClickListener;
import java.util.List;
public class CartFragment extends Fragment {
    private RecyclerView xrv_cart;
    private CartProvider provider;
    private Button btn_cart_edit;
    private Button btn_cart_calculate;
    private Button btn_cart_selectAll;
    private Button btn_cart_delete;
    private TextView tv_cart_totolPrice;
    private boolean isFirst;
    private int mPosition;
    private CartRvAdapter adapter;

    public static CartFragment getInstance() {
        return new CartFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, null);
        xrv_cart = v.findViewById(R.id.xrv_cart);
        btn_cart_edit = v.findViewById(R.id.btn_cart_edit);
        btn_cart_calculate = v.findViewById(R.id.btn_cart_calculate);
        btn_cart_selectAll = v.findViewById(R.id.btn_cart_selectAll);
        btn_cart_delete = v.findViewById(R.id.btn_cart_delete);
        tv_cart_totolPrice = v.findViewById(R.id.tv_cart_totolPrice);
        isFirst = true;
        btn_cart_edit.setText("编辑");
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xrv_cart.setLayoutManager(new LinearLayoutManager(getActivity()));
        showData();
    }

    private void showData() {
        provider = new CartProvider(getActivity());
        //从本地取出数据放入购物车中
        final List<CartGoodsBean> datas = provider.getAll();
        if(datas!=null){
            updateTotalPrice();

            adapter = new CartRvAdapter(getActivity(), datas);
            xrv_cart.setAdapter(adapter);
            adapter.notifyItemRemoved(mPosition);
            adapter.setXOnItemClickListener(new XOnItemClickListener() {
                @Override
                public void OnItemClick(String viewTag, int position) {
                    switch (viewTag){
                        case "shape_btn_cart_add":
                            provider.update(datas.get(position));
                            break;
                        case "btn_cart_sub":
                            provider.sub(datas.get(position));
                            break;
                    }
                }
            });

            btn_cart_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFirst){
                        isFirst = false;
                        btn_cart_calculate.setVisibility(View.GONE);
                        btn_cart_delete.setVisibility(View.VISIBLE);
                        btn_cart_edit.setText("完成");
                    }else {
                        isFirst = true;
                        btn_cart_calculate.setVisibility(View.VISIBLE);
                        btn_cart_delete.setVisibility(View.GONE);
                        btn_cart_edit.setText("编辑");
                    }
                }
            });

            btn_cart_selectAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFirst){
                        isFirst = false;
                        btn_cart_selectAll.setText("反选");
                        for (CartGoodsBean b:datas) {
                            b.isChecked = true;
                            provider.update(b);
                            adapter.notifyDataSetChanged();
                        }
                    }else {
                        isFirst = true;
                        btn_cart_selectAll.setText("全选");
                        for (CartGoodsBean b:datas) {
                            b.isChecked = false;
                            provider.update(b);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

            adapter.setXOnCheckedChangeListener(new XOnCheckedChangeListener() {
                @Override
                public void OnCheckedChangeListener(int position, boolean isChecked) {
                    mPosition = position;
                    datas.get(position).isChecked = isChecked;
                    provider.update(datas.get(position));
                    updateTotalPrice();
                }
            });

            btn_cart_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<CartGoodsBean> temp = provider.getAll();
                    for (CartGoodsBean b:temp) {
                        if(b.isChecked){
                            datas.remove(b);
                            provider.delete(b);
                        }
                    }
                    adapter.notifyItemRemoved(mPosition);
                    showData();
                }
            });

            //结算
            btn_cart_calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String totalPrice = updateTotalPrice();
                    Log.e("商品总价：",totalPrice);
                    Intent i = new Intent(getActivity(), CalculateActivity.class);
                    startActivity(i);
                }
            });
        }
    }



    private String updateTotalPrice() {
        List<CartGoodsBean> temp = provider.getAll();
        if(temp!=null){
            float total = 0;
            for (CartGoodsBean b:temp) {
                if(b.isChecked){
                    total = total+Float.parseFloat(b.getGoodsPrice());
                }
            }
            tv_cart_totolPrice.setText("合计：Y"+total);
        }
        return tv_cart_totolPrice.getText().toString();
    }
}
