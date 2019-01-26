package com.goufaning.warehouse.app.controller;

import com.goufaning.core.mvc.result.PageResultBean;
import com.goufaning.warehouse.app.entity.Warehouse;
import com.goufaning.warehouse.app.service.WarehouseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/warehouse")
@RestController
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_ADDRESS = "searchByAddress";
    private static final String SEARCH_ALL = "searchAll";

    @GetMapping("/all")
    public PageResultBean<List<Warehouse>> getAllWarehouseList(String searchType, int offset,  int limit, String keyWord) {
        List<Warehouse> rows = null;
        long total = 0;
        rows = query(searchType, keyWord, offset, limit);
        if (rows != null) {
            total = rows.size();
        }
        return new PageResultBean<List<Warehouse>>(rows, total);
    }

    private List<Warehouse> query(String searchType, String keyword, int offset, int limit) {
        Map<String, Object> queryResult = null;
        List<Warehouse> warehouseList = null;
        switch (searchType) {
            case SEARCH_BY_ID:
                if (StringUtils.isNumeric(keyword)) {
                    warehouseList = warehouseService.findById(Integer.valueOf(keyword));
                }
                break;
            case SEARCH_BY_ADDRESS:
                warehouseList = warehouseService.selectByAddress(offset, limit, keyword);
                break;
            case SEARCH_ALL:
                warehouseList = warehouseService.getAllWarehouse(offset, limit);
                break;
            default:
                // do other thing
                break;
        }
        return warehouseList;
    }



}
