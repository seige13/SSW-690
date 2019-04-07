package com.hobbymatcher.controller.user;

import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import com.hobbymatcher.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private Map<String, Object> listUser(HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<User> list = new ArrayList<User>();
        try {
            list = userService.getUserList();
            modelMap.put("list", list);
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            response.setStatus(400);
        }
        return modelMap;
    }

    //login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String passwordByMd5 = Md5.MD5(user.getPassWord());
        user=userService.findUserByEmail(user.getEmail());
        request.getSession().setAttribute("user", user);
        Boolean result = userService.login(user.getEmail(), passwordByMd5);
        modelMap.put("status", result);
        response.setStatus(result ? 200 : 401);
        return modelMap;
    }

    //logout
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> loginOut(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        request.getSession().invalidate();
        modelMap.put("status", true);
        modelMap.put("msg", "logout success");
        response.setStatus(200);
        return modelMap;
    }

    //toRegister
//    @RequestMapping(value = "toAdd")
//    public String toAdd() {
//        return "register";
//    }

    //register
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody User user, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (user != null) {
            System.out.println(user.getPassWord());
            Boolean result = userService.register(user);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        } else {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }
    }

    //delete user
    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestBody User user, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        User user1 = userService.findUserByEmail(user.getEmail());
        if (user1 != null) {
            Boolean result = userService.deleteUser(user1.getId());
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        }
        modelMap.put("status", false);
        response.setStatus(400);
        return modelMap;
    }

    //update user
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody User user, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (user != null) {
            Boolean result = userService.updateUser(user);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        } else {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }

    }
}
