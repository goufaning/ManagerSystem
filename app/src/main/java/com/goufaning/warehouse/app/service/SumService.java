package com.goufaning.warehouse.app.service;


import com.goufaning.warehouse.app.bean.InBaobiaoSum;
import com.goufaning.warehouse.app.bean.OutBaobiaoSum;
import com.goufaning.warehouse.app.dao.SumDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-05.
 */
public class SumService {
    SumDao dao = new SumDao();
    public List<InBaobiaoSum> getIn(int days) {
        return dao.findIn(days);
    }
    public List<OutBaobiaoSum> getOut(int days) {
        return dao.findOut(days);
    }
}
