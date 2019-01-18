package com.goufaning.system.controller;

import com.goufaning.system.entity.Warehouse;
import com.goufaning.system.service.IWarehouseService;
import com.goufaning.system.util.Result;
import com.goufaning.system.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class WarehouseController {
    @Autowired
    private IWarehouseService warehouseService;

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_ADDRESS = "searchByAddress";
    private static final String SEARCH_ALL = "searchAll";

    @RequestMapping("/searchWarehouse")
    public ModelAndView view() {
        return new ModelAndView("searchWarehouse");
    }

    @RequestMapping("/warehouse/list")
    public Map<String, Object> getAllWarehouseList(@RequestParam("searchType") String searchType,
                                                   @RequestParam("offset") int offset, @RequestParam("limit") int limit,
                                                   @RequestParam("keyWord") String keyWord) {
        Result result = ResultUtil.newResult();
        List<Warehouse> rows = null;
        long total = 0;
        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        if (queryResult != null) {
            rows = (List<Warehouse>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }

        // 设置 Response
        result.setCustomerInfo("rows", rows);
        result.setResponseTotal(total);
        return result.getResultMap();
    }

    private Map<String, Object> query(String searchType, String keyword, int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_ID:
                if (StringUtils.isNumeric(keyword)) {
                    queryResult = warehouseService.findById(Integer.valueOf(keyword));
                }
                break;
            case SEARCH_BY_ADDRESS:
                queryResult = warehouseService.selectByAddress(offset, limit, keyword);
                break;
            case SEARCH_ALL:
                queryResult = warehouseService.getAllWarehouse(offset, limit);
                break;
            default:
                // do other thing
                break;
        }
        return queryResult;
    }


}
