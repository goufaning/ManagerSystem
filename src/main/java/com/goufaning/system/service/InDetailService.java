package com.goufaning.system.service;

import com.goufaning.system.bean.InDetail;
import com.goufaning.system.dao.InDetailDao;

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
