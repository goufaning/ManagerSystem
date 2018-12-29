package com.goufaning.system.servlet;

import com.goufaning.system.bean.*;
import com.goufaning.system.service.OutDetailService;
import com.goufaning.system.service.OutService;
import com.goufaning.system.service.SumService;
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
@WebServlet(name = "OutBaobiaoServlet")
public class OutBaobiaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutService outService = new OutService();
        OutDetailService outDetailService = new OutDetailService();
        List<OutTable> outTableList = outService.getAll();
        List<OutDetail> outDetailList = outDetailService.getAll();
        List<OutBaobiao> outbaobiaos = new LinkedList<>();
        List<OutBaobiao> dayoutbaobiaos = new LinkedList<>();
        List<OutBaobiao> monthoutbaobiaos = new LinkedList<>();
        List<OutBaobiao> seasonoutbaobiaos = new LinkedList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OutTable outTable : outTableList) {
            for (OutDetail outDetail : outDetailList) {
                if (outDetail.getOutId() == outTable.getId()) {
                    OutBaobiao baobiao = new OutBaobiao();
                    baobiao.setProductId(outDetail.getProductId());
                    baobiao.setProviderId(outTable.getProviderId());
                    baobiao.setWarehouseId(outTable.getWarehouseId());
                    baobiao.setWorkerId(outTable.getWorkerId());
                    baobiao.setDate(outTable.getDate());
                    baobiao.setPrice(outDetail.getPrice());
                    baobiao.setNumber(outDetail.getNumber());
                    outbaobiaos.add(baobiao);
                    if (DateUtils.isSameDay(outTable.getDate(),new Date())) {
                        dayoutbaobiaos.add(baobiao);
                    }
                    if (((int) ((new Date().getTime() - outTable.getDate().getTime()) / (1000*3600*24))) <= 30) {
                        monthoutbaobiaos.add(baobiao);
                    }
                    if (((int) ((new Date().getTime() - outTable.getDate().getTime()) / (1000*3600*24))) <= 90) {
                        seasonoutbaobiaos.add(baobiao);
                    }
                }
            }
        }
        SumService sumService = new SumService();
        List<OutBaobiaoSum> outBaobiaoday = sumService.getOut(1);
        List<OutBaobiaoSum> outBaobiaomonth = sumService.getOut(30);
        List<OutBaobiaoSum> outBaobiaoseason = sumService.getOut(90);
        request.getSession().setAttribute("outBaobiaoday",outBaobiaoday);
        request.getSession().setAttribute("outBaobiaomonth",outBaobiaomonth);
        request.getSession().setAttribute("outBaobiaoseason",outBaobiaoseason);

        request.getSession().setAttribute("dayoutbaobiaos",dayoutbaobiaos);
        request.getSession().setAttribute("monthoutbaobiaos",monthoutbaobiaos);
        request.getSession().setAttribute("seasonoutbaobiaos",seasonoutbaobiaos);
        response.sendRedirect(request.getContextPath() + "/baobiao.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
