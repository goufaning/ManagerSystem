package com.goufaning.warehouse.app.service;


import com.goufaning.warehouse.app.entity.Inventory;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getAllProductInventory();

    void addProduct(int productId, int num, int warehouseId);
}
