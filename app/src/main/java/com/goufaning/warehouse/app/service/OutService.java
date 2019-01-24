package com.goufaning.warehouse.app.service;


import com.goufaning.warehouse.app.bean.OutTable;
import com.goufaning.warehouse.app.dao.OutTableDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-05.
 */
public class OutService {
    OutTableDao dao = new OutTableDao();
    public boolean Out(OutTable outTable) {
        return dao.insert(outTable);
    }
    public List<OutTable> getAll() {
        return dao.findAll();
    }
    public int getMaxId() {
        return dao.getMaxId();
    }
}
