package com.muxi.shopdemo.rv;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.bean.HomeGoodsBean;
public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.HomeRVViewHolder> {
    private Context mContext;
    private HomeGoodsBean bean;
    private XOnItemClickListener listener;
    public HomeRVAdapter(Context context, HomeGoodsBean bean) {
        this.mContext = context;
        this.bean = bean;
    }
    @NonNull
    @Override
    public HomeRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rv_item_home, null, false);
        return new HomeRVViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull HomeRVViewHolder holder, final int position) {
        Glide.with(mContext).asBitmap().load(bean.result.get(position).brandPicUrl).into(holder.iv_home_cardview_left);

        Glide.with(mContext).load(bean.result.get(position).goods.get(0).goodsPicUrl).into(holder.iv_home_right_top);
        Glide.with(mContext).load(bean.result.get(position).goods.get(1).goodsPicUrl).into(holder.iv_home_right_center);
        Glide.with(mContext).load(bean.result.get(position).goods.get(2).goodsPicUrl).into(holder.iv_home_right_bottom);

        holder.tv_home_right_top_title.setText(bean.result.get(position).goods.get(0).goodsTitle);
        holder.tv_home_right_center_title.setText(bean.result.get(position).goods.get(1).goodsTitle);
        holder.tv_home_right_bottom_title.setText(bean.result.get(position).goods.get(2).goodsTitle);

        holder.tv_home_right_top_price.setText("￥"+bean.result.get(position).goods.get(0).goodsPrice);
        holder.tv_home_right_center_price.setText("￥"+bean.result.get(position).goods.get(1).goodsPrice);
        holder.tv_home_right_bottom_price.setText("￥"+bean.result.get(position).goods.get(2).goodsPrice);

        holder.iv_home_right_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick("0",position);
            }
        });
        holder.iv_home_right_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick("1",position);
            }
        });
        holder.iv_home_right_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick("2",position);
            }
        });
    }
    @Override
    public int getItemCount() {
        if(bean!=null){
            return bean.result.size();
        }else {
            return 0;
        }
    }

    public void setXOnClickListener(XOnItemClickListener listener){
        this.listener = listener;
    }

    public class HomeRVViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_home_cardview_left;
        public ImageView iv_home_right_top;
        public ImageView iv_home_right_center;
        public ImageView iv_home_right_bottom;
        public TextView tv_home_right_top_title;
        public TextView tv_home_right_top_price;
        public TextView tv_home_right_center_title;
        public TextView tv_home_right_center_price;
        public TextView tv_home_right_bottom_title;
        public TextView tv_home_right_bottom_price;
        public HomeRVViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_home_cardview_left = itemView.findViewById(R.id.iv_home_cardview_left);

            iv_home_right_top = itemView.findViewById(R.id.iv_home_right_top);
            iv_home_right_center = itemView.findViewById(R.id.iv_home_right_center);
            iv_home_right_bottom = itemView.findViewById(R.id.iv_home_right_bottom);

            tv_home_right_top_title = itemView.findViewById(R.id.tv_home_right_top_title);
            tv_home_right_top_price = itemView.findViewById(R.id.tv_home_right_top_price);
            tv_home_right_center_title = itemView.findViewById(R.id.tv_home_right_center_title);
            tv_home_right_center_price = itemView.findViewById(R.id.tv_home_right_center_price);
            tv_home_right_bottom_title = itemView.findViewById(R.id.tv_home_right_bottom_title);
            tv_home_right_bottom_price = itemView.findViewById(R.id.tv_home_right_bottom_price);
        }
    }

}
