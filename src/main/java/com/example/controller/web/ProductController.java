package com.example.controller.web;

import com.example.dto.ProductDTO;
import com.example.service.IProductCategoryService;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "productControllerOfWeb")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductCategoryService productCategoryService;

    @RequestMapping(value = "/{seourl}-{id}", method = RequestMethod.GET)
    public ModelAndView courseAdvancedDetail(@PathVariable("id") long productId) {
        ModelAndView mav = new ModelAndView("web/product/detail");
        ProductDTO model = productService.findById(productId);
        mav.addObject("categories", productCategoryService.findAll());
        mav.addObject("comments", productService.findComment(productId));
        mav.addObject("model", model);
        return mav;
    }
}
