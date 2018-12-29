package com.goufaning.system.service;

import com.goufaning.system.bean.InTable;
import com.goufaning.system.dao.InTableDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-02.
 */
public class InService {
    InTableDao dao = new InTableDao();
    public boolean In(InTable inTable) {
        return dao.insert(inTable);
    }
    public int getMaxId() {
        return dao.getMaxId();
    }
    public List<InTable> getAll() {
        return dao.findAll();
    }
}
