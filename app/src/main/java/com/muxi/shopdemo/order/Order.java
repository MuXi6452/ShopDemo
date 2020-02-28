package com.muxi.shopdemo.order;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.muxi.shopdemo.bean.AlipayBean;

//商品订单相关操作
public class Order {
    public String TRADE_FINISHED = "TRADE_FINISHED";
    public String TRADE_SUCCESS = "TRADE_SUCCESS";
    public String TRADE_CLOSED = "TRADE_CLOSED";
    public String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    //更新订单状态
    public void updateOrderState(String outTradeNo){
        checkAlipay(outTradeNo);
    }

    //向支付宝发起订单查询请求
    public static Byte checkAlipay(String outTradeNo) {
        System.out.println("==================向支付宝发起查询，查询商户订单号为："+outTradeNo);
        // 1、获得初始化的AlipayClient
        String serverUrl = AlipayBean.getGatewayUrl();
        String appId = AlipayBean.getAppId();
        String privateKey = AlipayBean.getPrivateKey();
        String format = "json";
        String charset = AlipayBean.getCharset();
        String alipayPublicKey = AlipayBean.getPublicKey();
        String signType = AlipayBean.getSignType();
        String returnUrl = AlipayBean.getReturnUrl();
        String notifyUrl = AlipayBean.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        try {
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizContent("{" +
                    "\"out_trade_no\":\""+outTradeNo+"\"" +
                    "}");
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                switch (response.getTradeStatus()) // 判断交易结果
                {
                    case "TRADE_FINISHED": // 交易结束并不可退款
                        System.out.println("交易结束并不可退款");
                        break;
                    case "TRADE_SUCCESS": // 交易支付成功
                        System.out.println("交易支付成功");
                        break;
                    case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
                        System.out.println("未付款交易超时关闭或支付完成后全额退款");
                        break;
                    case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
                        System.out.println("交易创建并等待买家付款");
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("==================调用支付宝查询接口失败！");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
