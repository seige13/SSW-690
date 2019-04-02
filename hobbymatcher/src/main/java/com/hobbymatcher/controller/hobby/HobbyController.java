package com.hobbymatcher.controller.hobby;

import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    private Map<String, Object> listHobby(HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Hobby> list = new ArrayList<Hobby>();
        try {
            list = hobbyService.listHobby();
            modelMap.put("list", list);
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            response.setStatus(400);
        }
        return modelMap;
    }


    //addhobby
    @RequestMapping(value = "/addhobby", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody Hobby hobby, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (hobby != null) {
            Boolean result = hobbyService.insertHobby(hobby);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        }
        modelMap.put("status", false);
        response.setStatus(400);
        return modelMap;
    }

    //findhobby
    @RequestMapping(value = "/gethobby", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getHobby(String id, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try{
            int id1=Integer.parseInt(id);
            Hobby hobbyById = hobbyService.findHobbyById(id1);
            modelMap.put("hobby", hobbyById);
            response.setStatus(hobbyById == null ? 400 : 200);
        }catch (Exception e){
            modelMap.put("msg","valueError");
            modelMap.put("status",false);
            response.setStatus(400);
        }
        return modelMap;
    }


    //deletehobby
    @RequestMapping(value = "/deletehobby", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteHobby(String id, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try{
            int id1=Integer.parseInt(id);
            Boolean result = hobbyService.deleteHobby(id1);
            modelMap.put("status", result);
            response.setStatus(result? 200 : 400);
        }catch (Exception e){
            modelMap.put("msg","valueError");
            modelMap.put("status",false);
            response.setStatus(400);
        }
        return modelMap;
    }

}
