package com.hobbymatcher.controller.user;

import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionAttributes()
@Controller
@RequestMapping("/hobby")
public class HobbyController {

    private final HobbyService hobbyService;

    @Autowired
    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @RequestMapping(value = "/listhobby", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listHobby() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Hobby> list = new ArrayList<Hobby>();
        try {
            list = hobbyService.listHobby();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }


    //register
    @RequestMapping(value = "/addhobby", method = RequestMethod.POST)
    @ResponseBody
    public boolean add(String description, String classification, String constrains, String blogs, String status) {
        Hobby hobby = new Hobby();
        hobby.setBlogs(blogs);
        hobby.setClassification(classification);
        hobby.setConstrains(constrains);
        hobby.setDescription(description);
        hobby.setStatus(status);
        return hobbyService.insertHobby(hobby);
    }

    //register
    @RequestMapping(value = "/deletehobby", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteUser(String id) {
        return hobbyService.deleteHobby(id);
    }

}
