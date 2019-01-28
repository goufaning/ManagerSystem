package com.goufaning.warehouse.app.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PageController {

    @RequestMapping("/page/{page}")
    public ModelAndView welcome(@PathVariable String page) {
        return new ModelAndView(page);
    }

    @RequestMapping("/old")
    public ModelAndView mainView(HttpServletRequest request) {
        return new ModelAndView("inventory");
    }
}
