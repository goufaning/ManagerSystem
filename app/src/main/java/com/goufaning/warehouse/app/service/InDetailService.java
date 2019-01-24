package com.goufaning.warehouse.app.service;


import com.goufaning.warehouse.app.bean.InDetail;
import com.goufaning.warehouse.app.dao.InDetailDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-02.
 */
public class InDetailService {
    InDetailDao dao = new InDetailDao();
    public boolean In(InDetail inDetail) {
        return dao.insert(inDetail);
    }
    public List<InDetail> getAll() {
        return dao.findAll();
    }
}
