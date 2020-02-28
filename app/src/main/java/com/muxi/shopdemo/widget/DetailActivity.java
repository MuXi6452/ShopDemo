package com.muxi.shopdemo.widget;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.muxi.shopdemo.R;
import com.muxi.shopdemo.bean.AlipayBean;

public class DetailActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mWebView = findViewById(R.id.webView);
        initData();
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, String url) {
                final Activity context = DetailActivity.this;
                // ------  对alipays:相关的scheme处理 -------
//                if(url.startsWith("alipays:") || url.startsWith("alipay")) {
//                    try {
//                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
//                    } catch (Exception e) {
//                        new AlertDialog.Builder(context)
//                                .setMessage("未检测到支付宝客户端，请安装后重试。")
//                                .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Uri alipayUrl = Uri.parse("https://d.alipay.com");
//                                        context.startActivity(new Intent("android.intent.action.VIEW", alipayUrl));
//                                    }
//                                }).setNegativeButton("取消", null).show();
//                    }
//                    return true;
//                }
                if (!(url.startsWith("http") || url.startsWith("https"))) {
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
//        commitOrder();
        mWebView.loadUrl("http://ygr666.club:8081/xx?gid="+getIntent().getStringExtra("gid"));
//        mWebView.loadUrl("https://m.alipay.com/Gk8NF23");
        jsApi api = new jsApi();
        mWebView.addJavascriptInterface(api, "api");
    }

    private void commitOrder() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayBean.getAppId(),AlipayBean.getPrivateKey()
                        ,"json","GBK",AlipayBean.getPublicKey(),"RSA2");
                AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
                request.setReturnUrl("https://m.alipay.com/Gk8NF23");
                request.setBizContent("{" +
                        "\"body\":\"Iphone6 16G\"," +
                        "\"subject\":\"大乐透\"," +
                        "\"out_trade_no\":\"70501111111S001111119\"," +
                        "\"timeout_express\":\"90m\"," +
                        "\"time_expire\":\"2016-12-31 10:05\"," +
                        "\"total_amount\":9.00," +
                        "\"seller_id\":\"2088102147948060\"," +
                        "\"auth_token\":\"appopenBb64d181d0146481ab6a762c00714cC27\"," +
                        "\"goods_type\":\"0\"," +
                        "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                        "\"quit_url\":\"http://www.taobao.com/product/113714.html\"," +
                        "\"product_code\":\"QUICK_WAP_WAY\"," +
                        "\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
                        "\"royalty_info\":{" +
                        "\"royalty_type\":\"ROYALTY\"," +
                        "        \"royalty_detail_infos\":[{" +
                        "          \"serial_no\":1," +
                        "\"trans_in_type\":\"userId\"," +
                        "\"batch_no\":\"123\"," +
                        "\"out_relation_id\":\"20131124001\"," +
                        "\"trans_out_type\":\"userId\"," +
                        "\"trans_out\":\"2088101126765726\"," +
                        "\"trans_in\":\"2088101126708402\"," +
                        "\"amount\":0.1," +
                        "\"desc\":\"分账测试1\"," +
                        "\"amount_percentage\":\"100\"" +
                        "          }]" +
                        "    }," +
                        "\"extend_params\":{" +
                        "\"sys_service_provider_id\":\"2088511833207846\"," +
                        "\"hb_fq_num\":\"3\"," +
                        "\"hb_fq_seller_percent\":\"100\"," +
                        "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
                        "\"card_type\":\"S0JP0000\"" +
                        "    }," +
                        "\"sub_merchant\":{" +
                        "\"merchant_id\":\"19023454\"," +
                        "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
                        "    }," +
                        "\"merchant_order_no\":\"20161008001\"," +
                        "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                        "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                        "\"store_id\":\"NJ_001\"," +
                        "\"settle_info\":{" +
                        "        \"settle_detail_infos\":[{" +
                        "          \"trans_in_type\":\"cardAliasNo\"," +
                        "\"trans_in\":\"A0001\"," +
                        "\"summary_dimension\":\"A0001\"," +
                        "\"settle_entity_id\":\"2088xxxxx;ST_0001\"," +
                        "\"settle_entity_type\":\"SecondMerchant、Store\"," +
                        "\"amount\":0.1" +
                        "          }]" +
                        "    }," +
                        "\"invoice_info\":{" +
                        "\"key_info\":{" +
                        "\"is_support_invoice\":true," +
                        "\"invoice_merchant_name\":\"ABC|003\"," +
                        "\"tax_num\":\"1464888883494\"" +
                        "      }," +
                        "\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
                        "    }," +
                        "\"specified_channel\":\"pcredit\"," +
                        "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
                        "\"ext_user_info\":{" +
                        "\"name\":\"李明\"," +
                        "\"mobile\":\"16587658765\"," +
                        "\"cert_type\":\"IDENTITY_CARD\"," +
                        "\"cert_no\":\"362334768769238881\"," +
                        "\"min_age\":\"18\"," +
                        "\"fix_buyer\":\"F\"," +
                        "\"need_check_info\":\"F\"" +
                        "    }" +
                        "  }");
                AlipayTradeWapPayResponse response = null;
                try {
                    response = alipayClient.pageExecute(request);
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }
                if(response.isSuccess()){
                    Log.e("commitOrder","调用成功");
                } else {
                    Log.e("commitOrder","调用失败");
                }
            }
        }).start();
    }

    private void initData() {
        WebSettings s = mWebView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        s.setAllowFileAccess(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        //设置加载进来的页面自适应手机屏幕
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSupportZoom(false);
        s.setBuiltInZoomControls(false);

        s.setSupportMultipleWindows(true);
        s.setAppCacheEnabled(true);
        s.setDomStorageEnabled(true);
        s.setGeolocationEnabled(true);
        s.setAppCacheMaxSize(Long.MAX_VALUE);
        s.setPluginState(WebSettings.PluginState.ON_DEMAND);
        s.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    public class jsApi{
        @JavascriptInterface
        public void funcA(String outTradeNo) { //立即购买
//            Toast.makeText(DetailActivity.this, outTradeNo, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void funcB(String gid) { //加入购物车
            Toast.makeText(DetailActivity.this, gid, Toast.LENGTH_SHORT).show();
        }
    }
}
