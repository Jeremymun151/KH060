package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.EventDTO;
import com.example.dto.UserDTO;
import com.example.service.IEventService;
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

@Controller(value = "eventControllerOfAdmin")
public class EventController {

    @Autowired
    private IEventService eventService;

    @RequestMapping(value = "/admin/event/list", method = RequestMethod.GET)
    public ModelAndView getNews(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/event/list");
        List<EventDTO> users = eventService.findAll();
        initMessageResponse(mav, request);
        mav.addObject("events", users);
        return mav;
    }

    @RequestMapping(value = "/admin/event/edit", method = RequestMethod.GET)
    public ModelAndView editPostPage(@ModelAttribute("model") EventDTO model,
                                     @RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/event/edit");
        if (id != null) {
            model = eventService.findById(id);
        }
        initMessageResponse(mav, request);
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
