package com.muxi.shopdemo.rv;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.bean.HotGoodsBean;
import java.util.ArrayList;
public class HotRVAdapter extends RecyclerView.Adapter<HotRVAdapter.HotRVViewHolder> {
    private Context mContext;
    private ArrayList<HotGoodsBean> datas;
    private XOnItemClickListener listener;
    public HotRVAdapter(Context context, ArrayList<HotGoodsBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }
    @NonNull
    @Override
    public HotRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rv_item_hot, null, false);
        return new HotRVViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull HotRVViewHolder holder, final int position) {
        Glide.with(mContext).load(datas.get(position).getGoodsPicUrl()).dontAnimate().into(holder.iv_hot);
        holder.tv_hot_title.setText(datas.get(position).getGoodsTitle());
        holder.tv_hot_price.setText(datas.get(position).getGoodsPrice());
        holder.btn_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick("btn_hot",position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setXOnItemClickListener(XOnItemClickListener listener){
        this.listener = listener;
    }



    public class HotRVViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_hot;
        public TextView tv_hot_title;
        public TextView tv_hot_price;
        public Button btn_hot;
        public HotRVViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hot = itemView.findViewById(R.id.iv_hot);
            tv_hot_title = itemView.findViewById(R.id.tv_hot_title);
            tv_hot_price = itemView.findViewById(R.id.tv_hot_price);
            btn_hot = itemView.findViewById(R.id.btn_hot);
        }
    }
}
