package com.shopweb.bean;

import com.shopweb.bean.GoodInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ${chenxiaobo} on 2016/12/19.
 */
public class Cart {
    private HashMap<GoodInfo, Integer> goods;
    private double totolPrice;

    public Cart() {
        goods = new HashMap<GoodInfo, Integer>();
        totolPrice = 0.0;
    }

    //添加商品进入购物车
    public boolean addGoodToCart(GoodInfo goodInfo, int number) {
        if (goods.containsKey(goodInfo)) {
            goods.put(goodInfo, goods.get(goodInfo) + number);
        } else {
            goods.put(goodInfo, number);
        }
        cartTotalPrice();
        return true;
    }


    //从购物车里删除商品
    public boolean deleteGoodFromCart(GoodInfo goodInfo) {
        goods.remove(goodInfo);
        cartTotalPrice();
        return false;
    }

    //计算商品价格总和
    public double cartTotalPrice() {
        double sum = 0.0;
        Set<GoodInfo> key = goods.keySet();
        Iterator<GoodInfo> goodInfoIterator = key.iterator();
        while (goodInfoIterator.hasNext()) {
            GoodInfo goodInfo = goodInfoIterator.next();
            sum += Double.parseDouble(goodInfo.getRetail_price()) * goods.get(goodInfo);
        }
        this.setTotolPrice(sum);
        return this.getTotolPrice();
    }

    //修改商品数量
    public boolean updateCartInfo(GoodInfo goodInfo, int updateNumber) {
        if (goods.containsKey(goodInfo)) {
            goods.put(goodInfo, updateNumber);
            cartTotalPrice();
            return true;
        } else {
            cartTotalPrice();
            return false;
        }
    }

    public HashMap<GoodInfo, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<GoodInfo, Integer> goods) {
        this.goods = goods;
    }

    public double getTotolPrice() {
        return totolPrice;
    }

    public void setTotolPrice(double totolPrice) {
        this.totolPrice = totolPrice;
    }
}
