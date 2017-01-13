package com.goufaning.system.service;

import com.goufaning.system.bean.Inventory;
import com.goufaning.system.dao.InventoryDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-02.
 */
public class InventoryService {
    InventoryDao dao = new InventoryDao();
    public List<Inventory> getAllInventory() {
        return dao.findAll();
    }
}
