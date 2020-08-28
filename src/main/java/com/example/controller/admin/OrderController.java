package com.example.controller.admin;

import com.example.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "orderControllerOfAdmin")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/admin/order/list", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/order/list");
        mav.addObject("orders", orderService.findAll());
        return mav;
    }
}
