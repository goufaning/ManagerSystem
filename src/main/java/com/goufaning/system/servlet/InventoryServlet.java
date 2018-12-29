package com.goufaning.system.servlet;

import com.goufaning.system.bean.*;
import com.goufaning.system.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gfn on 2017-01-03.
 */
@WebServlet(name = "InventoryServlet")
public class InventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InventoryService inventoryService = new InventoryService();
        List<Inventory> inventoryList = inventoryService.getAllInventory();
        Map<Integer,Product> map = new HashMap<>();
        Map<Integer,WareHouse> map2 = new HashMap<>();
        Map<Integer,Provider> map3 = new HashMap<>();
        ProviderService providerService = new ProviderService();
        for (Provider provider : providerService.getAllProvider()) {
            map3.put(provider.getId(),provider);
        }
        Map<Integer,User> map4 = new HashMap<>();
        UserService userService = new UserService();
        for (User user : userService.getAllUser()) {
            map4.put(user.getId(),user);
        }
        WareHouseService wareHouseService = new WareHouseService();
        ProductService productService = new ProductService();
        for (Inventory inventory : inventoryList) {
            Product product = productService.findById(inventory.getProductId());
            map.put(inventory.getProductId(),product);
            WareHouse wareHouse = wareHouseService.findById(inventory.getWarehouseId());
            map2.put(inventory.getWarehouseId(),wareHouse);
        }
        request.getSession().setAttribute("inventoryList",inventoryList);
        request.getSession().setAttribute("productmap",map);
        request.getSession().setAttribute("warehousemap",map2);
        request.getSession().setAttribute("usermap",map4);
        request.getSession().setAttribute("providermap",map3);
        response.sendRedirect(request.getContextPath() + "/inventory.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
