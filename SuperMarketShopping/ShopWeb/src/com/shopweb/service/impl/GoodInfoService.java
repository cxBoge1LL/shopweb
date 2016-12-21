package com.shopweb.service.impl;

import com.shopweb.bean.GoodInfo;
import com.shopweb.dao.IGoodInfoDao;
import com.shopweb.service.IGoodInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

@Service
public class GoodInfoService implements IGoodInfoService {

    @Resource
    private IGoodInfoDao iGoodInfoDao;

    public List<GoodInfo> queryAllGoodInfo() {
        return iGoodInfoDao.queryAllGoodInfo();
    }

    public List<GoodInfo> queryGoodInfoByName(String name) {
        return iGoodInfoDao.queryGoodInfoByName(name);
    }

    public boolean addGoodInfo(GoodInfo goodInfo) throws Exception {
        try {
            return iGoodInfoDao.addGoodInfo(goodInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOne(int id) {
        return false;
    }

    public boolean deleteMore(String[] ids) {
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            idList.add(Integer.parseInt(ids[i]));
        }
        boolean b = iGoodInfoDao.deleteMore(idList);
        return b;
    }

    @Override
    public boolean updateGood(GoodInfo goodInfo) {
        return iGoodInfoDao.updateGood(goodInfo);
    }
}
