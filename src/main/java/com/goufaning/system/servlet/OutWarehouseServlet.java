package com.goufaning.system.servlet;

import com.goufaning.system.bean.*;
import com.goufaning.system.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gfn on 2017-01-02.
 */
public class OutWarehouseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer json = new StringBuffer();
        BufferedReader reader = request.getReader();
        String line = null;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        String j = json.toString();
        JSONObject jsonObject;
        JSONArray jsonArray = JSONArray.fromObject(j);
        List<TempObject> list = new ArrayList();
        String providerName = null;
        String warehouse = null;
        int number = jsonArray.length();
        String[] productName = new String[number];
        int[] num = new int[number];

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            providerName = jsonObject.getString("provider");
            warehouse = jsonObject.getString("warehouse");
            productName[i] = jsonObject.getString("prodect_name");
            num[i] = jsonObject.getInt("product_number");
        }
        User user = (User) request.getSession().getAttribute("user");
        OutService outService = new OutService();
        OutTable outTable = new OutTable();
        outTable.setWorkerId(user.getId());
        ProviderService providerService = new ProviderService();
        outTable.setProviderId(providerService.getByName(providerName).getId());
        WareHouseService wareHouseService = new WareHouseService();
        outTable.setWarehouseId(wareHouseService.getByName(warehouse).getId());
        outTable.setDate( new Date(System.currentTimeMillis() ));
        outService.Out(outTable);
        OutDetailService outDetailService = new OutDetailService();
        ProductService productService = new ProductService();
        for (int i = 0; i < number; i++ ) {
            OutDetail outDetail = new OutDetail();
            Product product = productService.findByName(productName[i]);
            outDetail.setNumber(num[i]);
            outDetail.setSizeId(1);
            outDetail.setUnitId(1);
            outDetail.setProductId(product.getId());
            outDetail.setPrice(product.getPrice());
            outDetail.setOutId(outService.getMaxId());
            outDetailService.In(outDetail);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
