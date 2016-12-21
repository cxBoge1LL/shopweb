package com.shopweb.controller;

import com.shopweb.bean.Cart;
import com.shopweb.bean.GoodInfo;
import com.shopweb.service.IGoodInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Administrator on 2016/12/12.
 */

@Controller
@RequestMapping("front")
public class GoodInfoAction {
    @Resource
    private IGoodInfoService iGoodInfoService;

    //商品列表初始化
    @RequestMapping("goodlist")
    public String showGoodList(Model model) {
        List<GoodInfo> goodLists = iGoodInfoService.queryAllGoodInfo();
        model.addAttribute("goodLists", goodLists);
        return "GoodInfo";
    }

    //通过关键词查询商品
    @RequestMapping("selectgood")
    @ResponseBody
    public List<GoodInfo> showSelectGood(String selectName) {
        List<GoodInfo> goodLists = iGoodInfoService.queryGoodInfoByName(selectName);
        return goodLists;
    }

    //添加商品
    @RequestMapping("addGood")
    @ResponseBody
    public boolean addGood(GoodInfo goodInfo, String product_standard_1, String product_standard_2) throws Exception {
        goodInfo.setProduct_standard(product_standard_1 + " " + product_standard_2);//规格和单位合二为一
        return iGoodInfoService.addGoodInfo(goodInfo);
    }

    //删除多条信息
    @RequestMapping("deleteMore")
    @ResponseBody
    public List<GoodInfo> deleteGood(@RequestParam(value = "ids[]") String[] ids) {
        boolean b = iGoodInfoService.deleteMore(ids);
        if (b) {
            List<GoodInfo> goodLists = iGoodInfoService.queryAllGoodInfo();
            return goodLists;
        } else {
            return null;
        }
    }

    //商品详情
    @RequestMapping("seeDetail")
    @ResponseBody
    public List<GoodInfo> seeDetail(@RequestParam(value = "id") String id) {
        List<GoodInfo> goodInfoList = iGoodInfoService.queryGoodInfoByName(id);
        return goodInfoList;
    }

    //修改信息界面
    @RequestMapping("updateInfo")
    public String updateInfo(@RequestParam(value = "id") String id, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        List<GoodInfo> goodInfoList = iGoodInfoService.queryGoodInfoByName(id);
        model.addAttribute("goodInfoList", goodInfoList);
        return "updateInfo";
    }

    //修改信息提交
    @RequestMapping("updateGood")
    @ResponseBody
    public boolean updateGood(GoodInfo goodInfo) {
        return iGoodInfoService.updateGood(goodInfo);
    }


    //加入购物车
    @RequestMapping("addCart")
    @ResponseBody
    public HashMap<GoodInfo, Integer> addCart(String id, HttpServletRequest request) {
        List<GoodInfo> goodInfoList = iGoodInfoService.queryGoodInfoByName(id);
        GoodInfo goodInfo = goodInfoList.get(0);//其实goodlist数据只有一个
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            Cart cart = new Cart();
            session.setAttribute("cart", cart);
        }
        Cart cart = (Cart) session.getAttribute("cart");
        boolean b = cart.addGoodToCart(goodInfo, 1);
        HashMap<GoodInfo, Integer> goods = cart.getGoods();
        return goods;
    }

    //删除购物车里的商品
    @RequestMapping("removeFromCart")
    @ResponseBody
    public HashMap<GoodInfo, Integer> removeFromCart(String id, HttpServletRequest request) {
        List<GoodInfo> goodInfoList = iGoodInfoService.queryGoodInfoByName(id);
        GoodInfo goodInfo = goodInfoList.get(0);//其实goodlist数据只有一个
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.deleteGoodFromCart(goodInfo);
        return cart.getGoods();
    }

    //修改商品数量
    @RequestMapping("updateCart")
    @ResponseBody
    public double updateCart(String id, int number, HttpServletRequest request) {
        List<GoodInfo> goodInfoList = iGoodInfoService.queryGoodInfoByName(id);
        GoodInfo goodInfo = goodInfoList.get(0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.updateCartInfo(goodInfo, number);
        return cart.cartTotalPrice();
    }

    //结算 删除session里保存的购物车信息  并更新相应数据库的库存
    @RequestMapping("checkOut")
    @ResponseBody
    public HashMap<GoodInfo, Integer> checkOut(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        HashMap<GoodInfo, Integer> goods = cart.getGoods();
        Set<GoodInfo> set = goods.keySet();
        Iterator iterator = set.iterator();
        //遍历购物车的商品信息
        while (iterator.hasNext()) {
            GoodInfo goodInfo = (GoodInfo) iterator.next();
            //将该商品的库存-购买数量设置成新的库存
            int oldRepertory = Integer.parseInt(goodInfo.getRepertory());//旧库存
            int goodNumber = goods.get(goodInfo);//购买商品数
            int newRepertory = oldRepertory - goodNumber;//辛库存
            goodInfo.setRepertory(String.valueOf(newRepertory));
            iGoodInfoService.updateGood(goodInfo);
        }
        request.getSession().removeAttribute("cart");
        return goods;
    }
}
