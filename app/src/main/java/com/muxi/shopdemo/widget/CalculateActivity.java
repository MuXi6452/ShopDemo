package com.muxi.shopdemo.widget;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class CalculateActivity extends AppCompatActivity {
    @BindView(R.id.btn_toPay)
    Button btnToPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_toPay)
    public void onViewClicked() {
        ToastUtil.showToast(this,"支付成功");
        finish();
    }
}
