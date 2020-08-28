package com.example.controller.web;

import com.example.dto.CartDTO;
import com.example.dto.UserDTO;
import com.example.service.IOrderService;
import com.example.service.IProductCategoryService;
import com.example.service.IUserService;
import com.example.util.SecurityUtils;
import com.example.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "orderControllerOfWeb")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductCategoryService productCategoryService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/don-hang", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("web/order/list");
        String name = SecurityUtils.getPrincipal().getUsername();
        mav.addObject("categories", productCategoryService.findAll());
        mav.addObject("orders", orderService.findByUser(name));
        return mav;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkoutPage(@RequestParam("totalPrice") Long totalPrice, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/checkout");
        String userName = SecurityUtils.getPrincipal().getUsername();
        List<CartDTO> cartDTOS = (List<CartDTO>) SessionUtils.getInstance().getValue(request, userName);
        UserDTO userDTO = userService.findById(SecurityUtils.getPrincipal().getId());
        mav.addObject("categories", productCategoryService.findAll());
        mav.addObject("userInfo", userDTO);
        mav.addObject("carts", cartDTOS);
        mav.addObject("totalPrice", totalPrice);
        return mav;
    }
}
