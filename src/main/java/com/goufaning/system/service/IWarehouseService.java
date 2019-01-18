package com.goufaning.system.service;

import com.goufaning.system.entity.Warehouse;

import java.util.Map;

public interface IWarehouseService {

    Map<String, Object> getAllWarehouse();

    Map<String, Object> getAllWarehouse(int offset, int limit);

    Map<String, Object> selectByAddress(String address);

    Map<String, Object> selectByAddress(int offset, int limit, String address);

    Map<String, Object> findById(int id);

    boolean addWarehouse(Warehouse warehouse);

    boolean updateWarehouse(Warehouse warehouse);

    boolean deleteWarehouse(int id);
}
