package com.hobbymatcher.controller.user;

import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import com.hobbymatcher.util.Md5;
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
@RequestMapping("/user")
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

    //login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    private boolean login(String email, String password) {
        String passwordByMd5 = Md5.MD5(password);
        return userService.login(email, passwordByMd5);
    }

    //toRegister
    @RequestMapping(value = "toAdd")
    public String toAdd() {
        return "register";
    }

    //register
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    @ResponseBody
    public boolean add(String email, String password, String nickname, String firstname, String lastname) {
        User user = new User();
        user.setNickName(nickname);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassWord(Md5.MD5(password));
        return userService.regist(user);
    }

}
