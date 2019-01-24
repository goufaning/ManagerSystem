package com.goufaning.warehouse.app.service;


import com.goufaning.warehouse.app.bean.Provider;
import com.goufaning.warehouse.app.dao.ProviderDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class ProviderService {
    ProviderDao dao = new ProviderDao();
    public List<Provider> getAllProvider() {
        return dao.findAll();
    }
    public Provider getByName(String name) {
        return dao.findByName(name);
    }
}
