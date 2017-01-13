package com.goufaning.system.service;

import com.goufaning.system.bean.OutTable;
import com.goufaning.system.dao.OutTableDao;

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
