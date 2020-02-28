package com.muxi.shopdemo.rv;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.bean.CartGoodsBean;
import java.util.List;
public class CartRvAdapter extends RecyclerView.Adapter<CartRvAdapter.CartRVViewHolder> {
    private Context mContext;
    private List<CartGoodsBean> beans;
    private XOnItemClickListener listener;
    private XOnCheckedChangeListener mCheckedChangeListener;
    public CartRvAdapter(Context context, List<CartGoodsBean> beans) {
        mContext = context;
        this.beans = beans;
    }

    @NonNull
    @Override
    public CartRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rv_item_cart, null, false);
        return new CartRVViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartRVViewHolder holder, final int position) {

        Glide.with(mContext).load(beans.get(position).getGoodsPicUrl()).dontAnimate().into(holder.iv_cart_goods_pic);
        holder.tv_cart_goods_name.setText(beans.get(position).getGoodsTitle());
        holder.tv_cart_goods_num.setText(beans.get(position).count + "");
        holder.tv_cart_goods_price.setText("Y"+beans.get(position).getGoodsPrice());
        holder.btn_cart_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick("shape_btn_cart_add", position);
                int num = Integer.parseInt(holder.tv_cart_goods_num.getText().toString());
                num++;
                holder.tv_cart_goods_num.setText(num + "");
            }
        });
        holder.btn_cart_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick("btn_cart_sub", position);
                int num = Integer.parseInt(holder.tv_cart_goods_num.getText().toString());
                if (num > 1) {
                    num--;
                    holder.tv_cart_goods_num.setText(num + "");
                } else {
                    return;
                }
            }
        });
        holder.checkbox_cart.setChecked(beans.get(position).isChecked);
        holder.checkbox_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCheckedChangeListener.OnCheckedChangeListener(position,isChecked);
                beans.get(position).isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (beans != null) {
            return beans.size();
        } else {
            return 0;
        }
    }

    public void setXOnItemClickListener(XOnItemClickListener listener) {
        this.listener = listener;
    }
    public void setXOnCheckedChangeListener(XOnCheckedChangeListener listener) {
        this.mCheckedChangeListener = listener;
    }


    public class CartRVViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_cart_goods_pic;
        public Button btn_cart_add;
        public Button btn_cart_sub;
        public TextView tv_cart_goods_num;
        public TextView tv_cart_goods_name;
        public TextView tv_cart_goods_price;
        public CheckBox checkbox_cart;
        public CartRVViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cart_goods_pic = itemView.findViewById(R.id.iv_cart_goods_pic);
            btn_cart_add = itemView.findViewById(R.id.btn_cart_add);
            btn_cart_sub = itemView.findViewById(R.id.btn_cart_sub);
            tv_cart_goods_num = itemView.findViewById(R.id.tv_cart_goods_num);
            tv_cart_goods_name = itemView.findViewById(R.id.tv_cart_goods_name);
            checkbox_cart = itemView.findViewById(R.id.checkbox_cart);
            tv_cart_goods_price = itemView.findViewById(R.id.tv_cart_goods_price);
        }
    }
}
