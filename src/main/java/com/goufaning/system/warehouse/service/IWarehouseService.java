package com.goufaning.system.warehouse.service;

import com.goufaning.system.warehouse.entity.Warehouse;

import java.util.List;

public interface IWarehouseService {

    List<Warehouse> getAllWareHouse();

    Warehouse getByName(String name);

    Warehouse findById(int id);
}
