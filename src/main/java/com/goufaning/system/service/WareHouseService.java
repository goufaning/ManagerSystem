package com.goufaning.system.service;

import com.goufaning.system.bean.WareHouse;
import com.goufaning.system.dao.WareHouseDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-02.
 */
public class WareHouseService {
    WareHouseDao dao = new WareHouseDao();
    public List<WareHouse> getAllWareHouse() {
        return dao.findAll();
    }
    public WareHouse getByName(String name) {
        return dao.findByName(name);
    }
    public WareHouse findById(int id) {
        return dao.findById(id);
    }
}
