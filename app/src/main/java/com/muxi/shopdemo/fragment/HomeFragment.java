package com.muxi.shopdemo.fragment;
import android.content.Intent;
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
import com.muxi.shopdemo.widget.DetailActivity;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.bean.HomeGoodsBean;
import com.muxi.shopdemo.rv.HomeRVAdapter;
import com.muxi.shopdemo.rv.XOnItemClickListener;
import com.muxi.shopdemo.util.Stream2String;
import com.muxi.xcarousel.CarouselFragment;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class HomeFragment extends Fragment {
    private RecyclerView mHomeRecyclerview;
    private String[] bannerPicUrls = {"http://img03.meituncdn.com/group1/M00/E0/B6/48675e0046994b689720faaf07847a1a.jpg"
            , "http://img04.meituncdn.com/group1/M00/AF/4A/aaff141f12ed4a1398c753fe031cd8c8.jpg"};
    public static HomeFragment getInstance() {
        return new HomeFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        mHomeRecyclerview = v.findViewById(R.id.home_recyclerview);
        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //加载轮播图
        getChildFragmentManager().beginTransaction().replace(R.id.framelayout_home, CarouselFragment.getInstance(bannerPicUrls),"CarouselFragment").commit();
        questNet();
    }
    private void questNet() {
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL u = new URL(strings[0]);
                    HttpURLConnection c = (HttpURLConnection) u.openConnection();
                    InputStream is = c.getInputStream();
                    String s = Stream2String.parseStream(is);
                    return s;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                final HomeGoodsBean bean = gson.fromJson(s, HomeGoodsBean.class);
                HomeRVAdapter adapter = new HomeRVAdapter(getActivity(), bean);
                mHomeRecyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                final Intent i = new Intent(getActivity(), DetailActivity.class);
                adapter.setXOnClickListener(new XOnItemClickListener() {
                    @Override
                    public void OnItemClick(String viewTag, int position) {
                        switch (viewTag) {
                            case "0":
                                i.putExtra("gid", bean.result.get(position).goods.get(0).goodsId);
                                break;
                            case "1":
                                i.putExtra("gid", bean.result.get(position).goods.get(1).goodsId);
                                break;
                            case "2":
                                i.putExtra("gid", bean.result.get(position).goods.get(2).goodsId);
                                break;
                        }
                        startActivity(i);
                    }
                });
            }
        };
        asyncTask.execute("http://ygr666.club/goods.json");
    }
}
