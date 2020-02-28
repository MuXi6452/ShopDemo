package com.muxi.shopdemo.fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.muxi.shopdemo.CartProvider;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.bean.CartGoodsBean;
import com.muxi.shopdemo.bean.HotGoodsBean;
import com.muxi.shopdemo.rv.HotRVAdapter;
import com.muxi.shopdemo.rv.XOnItemClickListener;
import com.muxi.shopdemo.util.Stream2String;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class HotFragment extends Fragment {
    private RecyclerView mHotRecyclerview;
    public static HotFragment getInstance() {
        return new HotFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hot, null);
        mHotRecyclerview = v.findViewById(R.id.hot_recyclerview);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHotRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        questNet();
    }

    private String mUrl = "http://ygr666.club/hot.json";
    private void questNet() {
        AsyncTask<String, Void, String> a = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL u = new URL(strings[0]);
                    HttpURLConnection c = (HttpURLConnection) u.openConnection();
                    InputStream is = c.getInputStream();
                    return Stream2String.parseStream(is);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonElements = jsonParser.parse(s).getAsJsonArray();//获取JsonArray对象
                    final ArrayList<HotGoodsBean> datas = new ArrayList<>();
                    for (JsonElement bean : jsonElements) {
                        HotGoodsBean b = gson.fromJson(bean, HotGoodsBean.class);//解析
                        datas.add(b);
                    }
                    HotRVAdapter adapter = new HotRVAdapter(getActivity(), datas);
                    mHotRecyclerview.setAdapter(adapter);
                    adapter.setXOnItemClickListener(new XOnItemClickListener() {
                        @Override
                        public void OnItemClick(String viewTag, int position) {
                            if (viewTag.equals("btn_hot")) {
                                CartProvider provider = new CartProvider(getActivity());
                                provider.put(convertData(datas.get(position)));
                            }
                        }
                    });
                }
            }
        };
        a.execute(mUrl);
    }

    private CartGoodsBean convertData(HotGoodsBean bean) {
        CartGoodsBean b = new CartGoodsBean();
        b.setGoodsId(bean.getGoodsId());
        b.setGoodsTitle(bean.getGoodsTitle());
        b.setGoodsPicUrl(bean.getGoodsPicUrl());
        b.setGoodsPrice(bean.getGoodsPrice());
        return b;
    }
}
