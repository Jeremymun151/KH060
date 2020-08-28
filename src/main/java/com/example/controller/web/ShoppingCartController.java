package com.example.controller.web;

import com.example.dto.CartDTO;
import com.example.service.IProductCategoryService;
import com.example.util.SecurityUtils;
import com.example.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "cartControllerOfWeb")
public class ShoppingCartController {

    @Autowired
    private IProductCategoryService productCategoryService;

    @RequestMapping(value = "/gio-hang", method = RequestMethod.GET)
    public ModelAndView cartPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/cart");
        String userName = SecurityUtils.getPrincipal().getUsername();
        List<CartDTO> cartDTOS = (List<CartDTO>) SessionUtils.getInstance().getValue(request, userName);
        if (cartDTOS != null && cartDTOS.size() > 0) {
            Integer totalPrice = 0;
            for (CartDTO item: cartDTOS) {
                if (item.getTotalPrice() != null) {
                    totalPrice += item.getTotalPrice();
                }
            }
            if (totalPrice != 0) {
                mav.addObject("totalPrice", totalPrice);
            }
            mav.addObject("carts", cartDTOS);
        }
        mav.addObject("categories", productCategoryService.findAll());
        return mav;
    }
}
