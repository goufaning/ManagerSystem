package com.goufaning.system.servlet;

import com.goufaning.system.user.entity.User;
import com.goufaning.system.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gfn on 2017-01-01.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService service = new UserService();
        User user = service.login(username,password);
        if (user==null) {
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('用户名或密码错误！');");
            out.println("history.back();");
            out.println("</script>");
        }else {
            request.getSession().setAttribute("user",user);
            response.sendRedirect(request.getContextPath() + "/PrepareServlet");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
