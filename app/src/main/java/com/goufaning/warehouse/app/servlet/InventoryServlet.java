package com.goufaning.warehouse.app.servlet;


import com.goufaning.warehouse.app.bean.Provider;
import com.goufaning.warehouse.app.entity.Product;
import com.goufaning.warehouse.app.entity.User;
import com.goufaning.warehouse.app.entity.Warehouse;
import com.goufaning.warehouse.app.service.ProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gfn on 2017-01-03.
 */
@WebServlet(name = "InventoryServlet")
public class InventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer,Product> map = new HashMap<>();
        Map<Integer,Warehouse> map2 = new HashMap<>();
        Map<Integer,Provider> map3 = new HashMap<>();
        ProviderService providerService = new ProviderService();
        for (Provider provider : providerService.getAllProvider()) {
            map3.put(provider.getId(),provider);
        }
        Map<Integer,User> map4 = new HashMap<>();
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
