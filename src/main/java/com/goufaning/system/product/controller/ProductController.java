package com.goufaning.system.product.controller;

import com.goufaning.system.product.entity.Inventory;
import com.goufaning.system.product.entity.Product;
import com.goufaning.system.product.service.IInventoryService;
import com.goufaning.system.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IInventoryService inventoryService;

    @RequestMapping("/main")
    public ModelAndView mainView(HttpServletRequest request) {
        return new ModelAndView("inventory");
    }

    @RequestMapping("/product/list")
    public List<Product> getAllProduct(HttpServletRequest request) {
        return productService.getAllProduct();
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
