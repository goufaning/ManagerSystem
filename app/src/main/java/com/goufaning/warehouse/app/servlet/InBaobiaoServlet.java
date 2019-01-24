package com.goufaning.warehouse.app.servlet;

import com.goufaning.warehouse.app.bean.*;
import com.goufaning.warehouse.app.service.InDetailService;
import com.goufaning.warehouse.app.service.InService;
import com.goufaning.warehouse.app.service.SumService;
import org.apache.commons.lang.time.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-04.
 */
@WebServlet(name = "InBaobiaoServlet")
public class InBaobiaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InService inService = new InService();
        InDetailService inDetailService = new InDetailService();
        List<InTable> inTableList = inService.getAll();
        List<InDetail> inDetailList = inDetailService.getAll();
        List<InBaobiao> baobiaos = new LinkedList<>();
        List<InBaobiao> daybaobiaos = new LinkedList<>();
        List<InBaobiao> mouthbaobiaos = new LinkedList<>();
        List<InBaobiao> seasonbaobiaos = new LinkedList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Sum> sumList1 = new LinkedList<>();
        List<Sum> sumList2 = new LinkedList<>();
        List<Sum> sumList3 = new LinkedList<>();
        for (InTable inTable : inTableList) {
            Sum sum = new Sum();
            for (InDetail inDetail : inDetailList) {
                if (inDetail.getInId() == inTable.getId()) {
                    InBaobiao baobiao = new InBaobiao();
                    baobiao.setProductId(inDetail.getProductId());
                    baobiao.setProviderId(inTable.getProviderId());
                    baobiao.setWarehouseId(inTable.getWarehouseId());
                    baobiao.setWorkerId(inTable.getWorkerId());
                    baobiao.setDate(inTable.getDate());
                    baobiao.setPrice(inDetail.getPrice());
                    baobiao.setNumber(inDetail.getNumber());
                    baobiaos.add(baobiao);
                    if (DateUtils.isSameDay(inTable.getDate(),new Date())) {
                        daybaobiaos.add(baobiao);
                    }
                    if (((int) ((new Date().getTime() - inTable.getDate().getTime() ) / (1000*3600*24))) <= 30) {
                        mouthbaobiaos.add(baobiao);
                    }
                    if (((int) ((new Date().getTime() - inTable.getDate().getTime()) / (1000*3600*24))) <= 90) {
                        seasonbaobiaos.add(baobiao);
                    }
                }
            }
        }
        SumService sumService = new SumService();
        List<InBaobiaoSum> inBaobiaoday = sumService.getIn(1);
        List<InBaobiaoSum> inBaobiaomonth = sumService.getIn(30);
        List<InBaobiaoSum> inBaobiaoseason = sumService.getIn(90);
        request.getSession().setAttribute("inBaobiaoday",inBaobiaoday);
        request.getSession().setAttribute("inBaobiaomonth",inBaobiaomonth);
        request.getSession().setAttribute("inBaobiaoseason",inBaobiaoseason);
        request.getSession().setAttribute("baobiaos",daybaobiaos);
        request.getSession().setAttribute("mouthbaobiaos",mouthbaobiaos);
        request.getSession().setAttribute("seasonbaobiaos",seasonbaobiaos);
        response.sendRedirect(request.getContextPath() + "/OutBaobiaoServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
