package com.goufaning.system.product.service;

import com.goufaning.system.product.entity.Inventory;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getAllProductInventory();

    void addProduct(int productId, int num, int warehouseId);
}
