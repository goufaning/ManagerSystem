package com.goufaning.warehouse.app.controller;


import com.goufaning.warehouse.app.entity.User;
import com.goufaning.warehouse.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    ModelAndView loginPage(HttpServletRequest request) {
        request.setAttribute("key", "hello world");
        return new ModelAndView("index");
    }

    @RequestMapping("/loginCheck")
    public User login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByNameAndPassword(username, password);
        return user;
    }

}
