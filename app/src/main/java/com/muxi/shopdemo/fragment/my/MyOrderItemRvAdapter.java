package com.muxi.shopdemo.fragment.my;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muxi.shopdemo.R;

public class MyOrderItemRvAdapter extends RecyclerView.Adapter<MyOrderItemRvAdapter.MyOrderItemViewHolder> {
    private Context mCtx;
    private String[] picUrls;
    public MyOrderItemRvAdapter(Context context,String[] picUrls) {
        this.mCtx = context;
        this.picUrls = picUrls;
    }

    @NonNull
    @Override
    public MyOrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //注意此次parent,false不然宽带铺不满屏幕
        View v = LayoutInflater.from(mCtx).inflate(R.layout.rv_item_my_order_item,parent,false);
        return new MyOrderItemViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyOrderItemViewHolder holder, int position) {
        for (int i = 0; i < 10; i++) {
            ImageView iv = new ImageView(mCtx);
            iv.setImageResource(R.mipmap.xtt);
            holder.llMyOrderPicPreview.addView(iv,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
    @Override
    public int getItemCount() {
        return 1;
    }
    public class MyOrderItemViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout llMyOrderPicPreview;
        public MyOrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            llMyOrderPicPreview = itemView.findViewById(R.id.ll_my_order_pic_preview);
        }
    }
}
