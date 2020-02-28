package com.muxi.shopdemo.fragment.my;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.muxi.shopdemo.R;
public class MyOrderItemFragment extends Fragment {
    RecyclerView xrvMyOrderItem;
    private String[] picUrls={};
    public static MyOrderItemFragment getInstance() {
        return new MyOrderItemFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_order_item, null);
        xrvMyOrderItem = v.findViewById(R.id.xrv_my_order_item);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyOrderItemRvAdapter adapter = new MyOrderItemRvAdapter(getActivity(), picUrls);
        xrvMyOrderItem.setAdapter(adapter);
        xrvMyOrderItem.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
