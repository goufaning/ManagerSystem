package com.goufaning.warehouse.app.controller;

import com.goufaning.warehouse.app.entity.Inventory;
import com.goufaning.warehouse.app.entity.Product;
import com.goufaning.warehouse.app.service.IInventoryService;
import com.goufaning.warehouse.app.service.IProductService;
import com.goufaning.warehouse.app.util.Result;
import com.goufaning.warehouse.app.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_NAME = "searchByName";
    private static final String SEARCH_ALL = "searchAll";

    @Autowired
    private IProductService productService;

    @Autowired
    private IInventoryService inventoryService;

    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) {
        Map<String, Object> queryResult = null;
        switch (searchType) {
            case SEARCH_BY_ID:
                if (StringUtils.isNumeric(keyWord))
                    queryResult = productService.selectById(Integer.valueOf(keyWord));
                break;
            case SEARCH_BY_NAME:
                queryResult = productService.selectByName(keyWord);
                break;
            case SEARCH_ALL:
                queryResult = productService.selectAll(offset, limit);
                break;
            default:
                // do other thing
                break;
        }

        return queryResult;
    }

    @RequestMapping("/product/list")
    public Map<String, Object> getAllProduct(@RequestParam("searchType") String searchType,
                                       @RequestParam("offset") int offset, @RequestParam("limit") int limit,
                                       @RequestParam("keyWord") String keyWord) {
        List<Product> rows = null;
        long total = 0;
        // 查询
        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        if (queryResult != null) {
            rows = (List<Product>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }
        Result result = ResultUtil.newResult();
        // 设置 Response
        result.setCustomerInfo("rows", rows);
        result.setResponseTotal(total);
        return result.getResultMap();
    }

    @RequestMapping("/product/add")
    public Map<String, Object> addProduct(@RequestBody Product product) {
        Result result = ResultUtil.newResult();

        String resultStr = productService.addProduct(product) ? Result.RESPONSE_RESULT_SUCCESS : Result.RESPONSE_RESULT_ERROR;
        result.setResponseResult(resultStr);
        return result.getResultMap();
    }

    @RequestMapping("/product/modify")
    public Map<String, Object> modifyProduct(@RequestBody Product product) {
        Result result = ResultUtil.newResult();
        String resultStr = productService.updateProduct(product) ? Result.RESPONSE_RESULT_SUCCESS : Result.RESPONSE_RESULT_ERROR;
        result.setResponseResult(resultStr);
        return result.getResultMap();
    }

    @RequestMapping("/product/delete")
    public Map<String, Object> deleteProduct(@RequestParam("goodsID") int productId) {
        Result result = ResultUtil.newResult();
        String resultStr = productService.deleteProduct(productId) ? Result.RESPONSE_RESULT_SUCCESS : Result.RESPONSE_RESULT_ERROR;
        result.setResponseResult(resultStr);
        return result.getResultMap();
    }





    @RequestMapping("/inventory/list")
    public List<Inventory> getProductInventory(HttpServletRequest request) {
        return inventoryService.getAllProductInventory();
    }

    @RequestMapping("/inventory/add")
    public Map<String, String> add(@RequestBody Map<String, Object> params) {
        int goodsID = Integer.parseInt((String) params.get("goodsID"));
        int number = Integer.parseInt((String) params.get("number"));
        int repositoryID = Integer.parseInt((String) params.get("repositoryID"));
        inventoryService.addProduct(goodsID, number, repositoryID);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

}
