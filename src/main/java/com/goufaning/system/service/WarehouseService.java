package com.goufaning.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goufaning.system.entity.Warehouse;
import com.goufaning.system.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    public Map<String, Object> getAllWarehouse() {
        return getAllWarehouse(-1, -1);
    }


    @Override
    public Map<String, Object> selectByAddress(String address) {
        return selectByAddress(-1, -1, address);
    }

    @Override
    public Map<String, Object> selectByAddress(int offset, int limit, String address) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Warehouse> warehouseList;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0) {
            isPagination = false;
        }
        if (isPagination) {
            PageHelper.offsetPage(offset, limit);
            warehouseList = warehouseMapper.selectByAddress(address);
            if (warehouseList != null) {
                PageInfo<Warehouse> pageInfo = new PageInfo<>(warehouseList);
                total = pageInfo.getTotal();
            } else {
                warehouseList = new ArrayList<>();
            }
        } else {
            warehouseList = warehouseMapper.selectByAddress(address);
            if (warehouseList != null) {
                total = warehouseList.size();
            } else {
                warehouseList = new ArrayList<>();
            }
        }
        resultSet.put("data", warehouseList);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public Map<String, Object> getAllWarehouse(int offset, int limit) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Warehouse> warehouseList;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0) {
            isPagination = false;
        }
        if (isPagination) {
            PageHelper.offsetPage(offset, limit);
            warehouseList = warehouseMapper.selectAll();
            if (warehouseList != null) {
                PageInfo<Warehouse> pageInfo = new PageInfo<>(warehouseList);
                total = pageInfo.getTotal();
            } else {
                warehouseList = new ArrayList<>();
            }
        } else {
            warehouseList = warehouseMapper.selectAll();
            if (warehouseList != null) {
                total = warehouseList.size();
            } else {
                warehouseList = new ArrayList<>();
            }
        }
        resultSet.put("data", warehouseList);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public Map<String, Object> findById(int id) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Warehouse> warehouseList = new ArrayList<>();
        long total = 0;
        Warehouse warehouse = warehouseMapper.findById(id);
        if (warehouse != null) {
            warehouseList.add(warehouse);
            total = 1;
        }
        resultSet.put("data", warehouseList);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public boolean addWarehouse(Warehouse warehouse) {
        if (warehouse != null) {
            warehouseMapper.insert(warehouse);
            if (warehouse.getId() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateWarehouse(Warehouse warehouse) {
        if (warehouse != null) {
            warehouseMapper.update(warehouse);
            if (warehouse.getId() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteWarehouse(int id) {
        warehouseMapper.deleteById(id);
        return true;
    }
}
