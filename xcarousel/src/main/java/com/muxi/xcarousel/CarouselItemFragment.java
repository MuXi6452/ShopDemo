package com.muxi.xcarousel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
public class CarouselItemFragment extends Fragment {
    public static CarouselItemFragment getInstance(){
        return new CarouselItemFragment();
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.carousel_imgs, null);
        ImageView iv_banner = v.findViewById(R.id.iv_banner);
        int position = getArguments().getInt("position");
        String[] bannerPicUrls = (String[])getArguments().getSerializable("bannerPicUrls");
        Glide.with(getContext()).load(bannerPicUrls[position]).dontAnimate().into(iv_banner);
        return v;
    }



}
