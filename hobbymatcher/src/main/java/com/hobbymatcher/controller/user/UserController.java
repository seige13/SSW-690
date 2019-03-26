package com.hobbymatcher.controller.user;

import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import com.hobbymatcher.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@SessionAttributes()
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    private boolean login(@RequestBody User user, HttpServletRequest request) {
        String passwordByMd5 = Md5.MD5(user.getPassWord());
        User user1 = userService.findUserByEmail(user.getEmail());
        if (user1 == null) {
            return false;
        }
        request.getSession().setAttribute("user", user);
        return userService.login(user1.getEmail(), passwordByMd5);
    }

    //toRegister
//    @RequestMapping(value = "toAdd")
//    public String toAdd() {
//        return "register";
//    }

    //register
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    @ResponseBody
    public boolean add(@RequestBody User user) {
        if (user != null) {
            user.setPassWord(Md5.MD5(user.getPassWord()));
            return userService.register(user);
        }
        return false;
    }

    //register
    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteUser(@RequestBody User user) {
        User user1 = userService.findUserByEmail(user.getEmail());
        if (user1 != null) {
            return userService.deleteUser(user.getId());
        }
        return false;
    }

}
