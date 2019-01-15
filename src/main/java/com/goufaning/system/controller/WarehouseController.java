package com.goufaning.system.controller;

import com.goufaning.system.entity.Warehouse;
import com.goufaning.system.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WarehouseController {
    @Autowired
    private IWarehouseService warehouseService;

    @RequestMapping("/searchWarehouse")
    public ModelAndView view() {
        return new ModelAndView("searchWarehouse");
    }

    @RequestMapping("/warehouse/list")
    public List<Warehouse> getAllWarehouseList() {
        return warehouseService.getAllWareHouse();
    }

}
