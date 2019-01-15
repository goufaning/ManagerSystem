package com.goufaning.system.service;

import com.goufaning.system.entity.Warehouse;

import java.util.List;

public interface IWarehouseService {

    List<Warehouse> getAllWareHouse();

    Warehouse getByName(String name);

    Warehouse findById(int id);
}
