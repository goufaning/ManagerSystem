package com.goufaning.warehouse.app.service;

import com.goufaning.warehouse.app.entity.Inventory;
import com.goufaning.warehouse.app.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryMapper mapper;

    public List<Inventory> getAllProductInventory() {
        return mapper.findAll();
    }

    public void addProduct(int productId, int num, int warehouseId) {
        mapper.add(productId, num, warehouseId, new Date());
    }
}
