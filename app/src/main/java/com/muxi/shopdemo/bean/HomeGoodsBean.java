package com.muxi.shopdemo.bean;
import java.io.Serializable;
import java.util.List;

public class HomeGoodsBean implements Serializable {
    public List<ResultBean> result;
    public class ResultBean implements Serializable{
        public String brandPicUrl;
        public List<GoodsBean> goods;
    }
    public class GoodsBean implements Serializable{
        public String goodsId;
        public String goodsPicUrl;
        public String goodsTitle;
        public String goodsPrice;
    }
}
