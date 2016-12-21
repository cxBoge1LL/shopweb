package com.shopweb.dao;

import com.shopweb.bean.GoodInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chenxiaobo} on 2016/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml"})
public class IGoodInfoDaoTest {

    @Resource
    private IGoodInfoDao iGoodInfoDao;

    @Test
    public void queryAllGoodInfo() throws Exception {
        List<GoodInfo> goodInfos = iGoodInfoDao.queryAllGoodInfo();
        for (GoodInfo goodInfo : goodInfos) {
            System.out.println(goodInfo);
        }

    }

    @Test
    public void queryGoodInfoByName() throws Exception {
        List<GoodInfo> goodInfos = iGoodInfoDao.queryGoodInfoByName("奶糖");
        for (GoodInfo goodInfo : goodInfos) {
            System.out.println(goodInfo);
        }
    }

    @Test
    public void addGoodInfo() throws Exception {
        GoodInfo goodInfo=new GoodInfo();
        goodInfo.setBar_code(123123123);
        goodInfo.setProduct_id(1000222);
        goodInfo.setExpiration_date("2016/12/13");
        goodInfo.setProduct_name("牛肉面");
        goodInfo.setProduct_standard("包");
        goodInfo.setPurchase_price("100");
        goodInfo.setRepertory("1100");
        goodInfo.setType("食品");
        goodInfo.setUnit("kg");
        goodInfo.setRetail_price("100");
        boolean b = iGoodInfoDao.addGoodInfo(goodInfo);
        System.out.println(b);

    }

    @Test
    public void deleteOne() throws Exception {

    }

    @Test
    public void deleteMore() throws Exception {
        List<Integer> list=new ArrayList<>();
        list.add(31233);
        boolean b = iGoodInfoDao.deleteMore(list);
        System.out.println(b);
    }

    @Test
    public void updateGood() throws Exception {
        GoodInfo goodInfo=new GoodInfo();
        goodInfo.setBar_code(123123123);
        goodInfo.setProduct_id(1000222);
        goodInfo.setExpiration_date("2016/12/13");
        goodInfo.setProduct_name("红烧牛肉面");
        goodInfo.setProduct_standard("包");
        goodInfo.setPurchase_price("100");
        goodInfo.setRepertory("1100");
        goodInfo.setType("食品");
        goodInfo.setUnit("kg");
        goodInfo.setRetail_price("100");
        boolean b = iGoodInfoDao.updateGood(goodInfo);
        System.out.println(b);
    }


}