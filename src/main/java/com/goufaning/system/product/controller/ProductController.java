package com.goufaning.system.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductController {

    @RequestMapping("/main")
    public ModelAndView mainView(HttpServletRequest request) {
        return new ModelAndView("inventory");
    }
}
