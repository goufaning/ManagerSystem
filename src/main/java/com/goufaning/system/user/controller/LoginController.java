package com.goufaning.system.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/login")
    String loginPage(HttpServletRequest request) {
        request.setAttribute("key", "hello world");
        return "index";
    }
}
