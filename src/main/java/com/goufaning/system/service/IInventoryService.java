package com.goufaning.system.service;

import com.goufaning.system.entity.Inventory;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getAllProductInventory();

    void addProduct(int productId, int num, int warehouseId);
}
