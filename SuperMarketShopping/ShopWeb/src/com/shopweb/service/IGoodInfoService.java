package com.shopweb.service;

import com.shopweb.bean.GoodInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface IGoodInfoService {


    //查询所有商品信息在
    List<GoodInfo> queryAllGoodInfo();

    //商品名模糊查询
    List<GoodInfo> queryGoodInfoByName(String name);

    //添加商品信息
    boolean addGoodInfo(GoodInfo goodInfo) throws Exception;

    //删除商品信息
    boolean deleteOne(int id);

    //删除多条信息
    boolean deleteMore(String[] ids);

    boolean updateGood(GoodInfo goodInfo);
}
