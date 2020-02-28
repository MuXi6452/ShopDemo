package com.muxi.shopdemo.fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.muxi.shopdemo.widget.CircleImageDrawable;
import com.muxi.shopdemo.widget.MainActivity;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.util.PreferencesUtil;

import java.io.FileNotFoundException;
public class ChangeProfileFragment extends Fragment {
    private ImageView mIvProfilePreview;
    private Button mBtnChangeProfile;
    private Button mBtnChangeProfileYes;
    private Button mBtnChangeProfileNo;
    private ImageView iv_my_profile;
    private Bitmap bitmap;
    private Uri uri;

    public static ChangeProfileFragment getInstance() {
        return new ChangeProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_profile, null);
        mIvProfilePreview = v.findViewById(R.id.iv_profile_preview);
        mBtnChangeProfile = v.findViewById(R.id.btn_change_profile);
        mBtnChangeProfileYes = v.findViewById(R.id.btn_change_profile_yes);
        mBtnChangeProfileNo = v.findViewById(R.id.btn_change_profile_no);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String profile_path = PreferencesUtil.getString(getActivity(), "profile_path");
        if (profile_path != null) {
            Log.e("profile_path:",profile_path);
            ImageView iv = getActivity().findViewById(R.id.iv_profile_preview);
            Glide.with(getActivity()).load(profile_path).into(iv);
        }
        mBtnChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() instanceof MainActivity){
                    MainActivity a = (MainActivity) getActivity();
                    TabLayout b = a.findViewById(R.id.bottom_nav);
                    b.setVisibility(View.GONE);
                }
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT){
                    startActivityForResult(intent, REQUEST_CODE);
                }else{
                    startActivityForResult(intent, REQUEST_CODE);
                }
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.setType("image/*");
//                startActivityForResult(i, REQUEST_CODE);
                mBtnChangeProfile.setVisibility(View.GONE);
                mBtnChangeProfileYes.setVisibility(View.VISIBLE);
                mBtnChangeProfileNo.setVisibility(View.VISIBLE);
            }
        });
        mBtnChangeProfileYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
                if(getActivity() instanceof MainActivity){
                    MainActivity a = (MainActivity) getActivity();
                    TabLayout b = a.findViewById(R.id.bottom_nav);
                    b.setVisibility(View.VISIBLE);
                }
            }
        });
        mBtnChangeProfileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnChangeProfileNo.setVisibility(View.GONE);
                mBtnChangeProfileYes.setVisibility(View.GONE);
                mBtnChangeProfile.setVisibility(View.VISIBLE);
            }
        });
    }

    private int REQUEST_CODE = 1005;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            uri = data.getData();
            PreferencesUtil.putString(getActivity(),"profile_path",uri.toString());
            ContentResolver cr = getActivity().getContentResolver();
            try {
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                CircleImageDrawable c = new CircleImageDrawable(bitmap);
                ImageView iv = getActivity().findViewById(R.id.iv_my_profile);
                iv.setImageDrawable(c);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Glide.with(this).asBitmap().load(bitmap).into(mIvProfilePreview);
        }
    }
}
