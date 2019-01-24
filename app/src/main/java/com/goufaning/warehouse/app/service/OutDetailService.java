package com.goufaning.warehouse.app.service;


import com.goufaning.warehouse.app.bean.OutDetail;
import com.goufaning.warehouse.app.dao.OutDetailDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-02.
 */
public class OutDetailService {
    OutDetailDao dao = new OutDetailDao();
    public boolean In(OutDetail outDetail) {
        return dao.insert(outDetail);
    }
    public List<OutDetail> getAll() {
        return dao.findAll();
    }
}
