package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.FeatureDTO;
import com.example.dto.ProductCategoryDTO;
import com.example.service.IFeatureService;
import com.example.service.IProductCategoryService;
import com.example.util.MessageResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller(value = "featureControllerOfAdmin")
public class FeatureController {

    @Autowired
    private IFeatureService featureService;

    @Autowired
    private IProductCategoryService productCategoryService;

    @RequestMapping(value = "/admin/feature/list", method = RequestMethod.GET)
    public ModelAndView getNews(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/feature/list");
        List<FeatureDTO> featureDTOS = featureService.findAll();
        initMessageResponse(mav, request);
        mav.addObject("features", featureDTOS);
        return mav;
    }

    @RequestMapping(value = "/admin/feature/edit", method = RequestMethod.GET)
    public ModelAndView editPostPage(@ModelAttribute("model") FeatureDTO model,
                                     @RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/feature/edit");
        if (id != null) {
            model = featureService.findById(id);
        }
        initMessageResponse(mav, request);
        mav.addObject("categories", productCategoryService.getCategories());
        mav.addObject("model", model);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = MessageResponseUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
