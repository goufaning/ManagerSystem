package com.goufaning.system.warehouse.controller;

import com.goufaning.system.warehouse.entity.Warehouse;
import com.goufaning.system.warehouse.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarehouseController {
    @Autowired
    private IWarehouseService warehouseService;

    @RequestMapping("/warehouse/list")
    public List<Warehouse> getAllWarehouseList() {
        return warehouseService.getAllWareHouse();
    }

}
