package com.hobbymatcher.controller.superadmin;

import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listuser", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listUser() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<User> list = new ArrayList<User>();
        try {
            list = userService.getUserList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }
}
