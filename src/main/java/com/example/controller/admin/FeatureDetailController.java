package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.FeatureDetailDTO;
import com.example.dto.UserDTO;
import com.example.service.IFeatureDetailService;
import com.example.service.IFeatureService;
import com.example.service.IRoleService;
import com.example.service.IUserService;
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

@Controller(value = "featureDetailControllerOfAdmin")
public class FeatureDetailController {

    @Autowired
    private IFeatureDetailService featureDetailService;

    @Autowired
    private IFeatureService featureService;

    @RequestMapping(value = "/admin/featuredetail/list", method = RequestMethod.GET)
    public ModelAndView getNews(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/featuredetail/list");
        List<FeatureDetailDTO> featureDetailDTOS = featureDetailService.findAllVersion2();
        initMessageResponse(mav, request);
        mav.addObject("featuredetails", featureDetailDTOS);
        return mav;
    }

    @RequestMapping(value = "/admin/featuredetail/edit", method = RequestMethod.GET)
    public ModelAndView editPostPage(@ModelAttribute("model") FeatureDetailDTO model,
                                     @RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/featuredetail/edit");
        if (id != null) {
            model = featureDetailService.findById(id);
        }
        initMessageResponse(mav, request);
        mav.addObject("features", featureService.getFeatures());
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
