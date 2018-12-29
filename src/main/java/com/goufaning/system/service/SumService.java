package com.goufaning.system.service;

import com.goufaning.system.bean.InBaobiaoSum;
import com.goufaning.system.bean.OutBaobiaoSum;
import com.goufaning.system.dao.SumDao;

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
