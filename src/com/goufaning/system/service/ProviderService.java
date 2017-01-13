package com.goufaning.system.service;

import com.goufaning.system.bean.Provider;
import com.goufaning.system.dao.ProviderDao;

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
