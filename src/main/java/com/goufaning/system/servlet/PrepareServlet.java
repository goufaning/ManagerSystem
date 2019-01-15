package com.goufaning.system.servlet;

import com.goufaning.system.entity.Product;
import com.goufaning.system.bean.Provider;
import com.goufaning.system.service.ProductService;
import com.goufaning.system.service.ProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
@WebServlet(name = "PrepareServlet")
public class PrepareServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        List<Product> productList = productService.getAllProduct();
        request.getSession().setAttribute("productList",productList);

        ProviderService providerService = new ProviderService();
        List<Provider> providerList = providerService.getAllProvider();
        request.getSession().setAttribute("providerList",providerList);


        response.sendRedirect(request.getContextPath() + "/InventoryServlet");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
