package com.goufaning.system.service;

import com.goufaning.system.entity.Warehouse;
import com.goufaning.system.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> getAllWareHouse() {
        return warehouseMapper.findAll();
    }

    @Override
    public Warehouse getByName(String name) {
        return warehouseMapper.findByName(name);
    }

    @Override
    public Warehouse findById(int id) {
        return warehouseMapper.findById(id);
    }
}
