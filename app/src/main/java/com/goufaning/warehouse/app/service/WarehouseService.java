package com.goufaning.warehouse.app.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goufaning.warehouse.app.entity.Warehouse;
import com.goufaning.warehouse.app.mapper.WarehouseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    @Autowired
    private WarehouseMapper warehouseMapper;

    public List<Warehouse> getAllWarehouse() {
        logger.info("getAll warehouse start...");
        List<Warehouse> list = getAllWarehouse(-1, -1);
        logger.info("getAll warehouse end, data size:{}", list.size());
        return list;
    }


    public List<Warehouse> selectByAddress(String address) {
        return selectByAddress(-1, -1, address);
    }

    public List<Warehouse> selectByAddress(int offset, int limit, String address) {
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
        return warehouseList;
    }

    public List<Warehouse> getAllWarehouse(int offset, int limit) {
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
        return warehouseList;
    }

    public List<Warehouse> findById(int id) {
        List<Warehouse> warehouseList = new ArrayList<>();
        Warehouse warehouse = warehouseMapper.findById(id);
        if (warehouse != null) {
            warehouseList.add(warehouse);
        }
        return warehouseList;
    }

    public boolean addWarehouse(Warehouse warehouse) {
        if (warehouse != null) {
            warehouseMapper.insert(warehouse);
            if (warehouse.getId() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean updateWarehouse(Warehouse warehouse) {
        if (warehouse != null) {
            warehouseMapper.update(warehouse);
            if (warehouse.getId() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteWarehouse(int id) {
        warehouseMapper.deleteById(id);
        return true;
    }
}
