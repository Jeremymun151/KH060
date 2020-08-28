package com.example.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dto.FeatureSearchDTO;
import com.example.dto.HomeSearchDTO;
import com.example.service.IBrandService;
import com.example.service.IFeatureDetailService;
import com.example.service.IProductCategoryService;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private IProductCategoryService productCategoryService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IFeatureDetailService featureDetailService;

	@Autowired
	private IBrandService brandService;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("model") HomeSearchDTO model) {
		ModelAndView mav = new ModelAndView("web/home");
		if (model.getProductCategoryCode() == null) {
			model.setProductCategoryCode("bong-den-trang-tri");
		}
		mav.addObject("productCategoryCode", model.getProductCategoryCode());
		mav.addObject("categories", productCategoryService.findAll());
		mav.addObject("features", featureDetailService.findByProductCategory(model.getProductCategoryCode()));
		mav.addObject("brands", brandService.findByProductCategory(model.getProductCategoryCode()));
		mav.addObject("products", productService.findAll(model));
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}
}
