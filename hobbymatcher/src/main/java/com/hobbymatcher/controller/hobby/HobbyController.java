package com.hobbymatcher.controller.user;

import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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
    public boolean add(@RequestBody Hobby hobby) {
        if (hobby != null) {
            hobby.setBlogs(hobby.getBlogs());
            hobby.setClassification(hobby.getClassification());
            hobby.setConstrains(hobby.getConstrains());
            hobby.setDescription(hobby.getDescription());
            hobby.setStatus(hobby.getStatus());
            return hobbyService.insertHobby(hobby);
        }
        return false;
    }

    //register
    @RequestMapping(value = "/deletehobby", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteUser(@RequestBody Hobby hobby) {
        if (hobby != null) {
            return hobbyService.deleteHobby(hobby.getHobbyId());
        }
        return false;
    }

}
