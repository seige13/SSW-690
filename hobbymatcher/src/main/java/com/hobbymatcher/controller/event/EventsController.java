package com.hobbymatcher.controller.event;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.service.EventsService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@SessionAttributes()
@Controller
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @RequestMapping(value = "/listevents", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listEvents(HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Events> list = new ArrayList<Events>();
        try {
            list = eventsService.getEventsList();
            modelMap.put("list", list);
            modelMap.put("status", true);
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("status", false);
            modelMap.put("msg", e.toString());
            response.setStatus(400);
        }
        return modelMap;
    }


    @RequestMapping(value = "/addevents", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestPart("events") Events events, @RequestPart("file") MultipartFile imageFile, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (events != null) {
            if (imageFile != null)
            {
                String filePath = "webapp" + File.separator + "resources" + File.separator + "image" + File.separator;
                String originalFilename = imageFile.getOriginalFilename();
                File dir = new File(filePath);
                if(!dir.exists())
                {
                    dir.mkdirs();
                }
                String newFileName = UUID.randomUUID() + originalFilename;
                File targetFile = new File(filePath, newFileName);
                try {
                    imageFile.transferTo(targetFile);
                    events.setEventsImage(targetFile.getAbsolutePath());
                } catch (Exception e) {
                    System.out.print(e.toString());
                    modelMap.put("status", false);
                    response.setStatus(400);
                }
            }
            Boolean result = eventsService.addEvents(events);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        }else {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }
    }

    @RequestMapping(value = "/deleteevents", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteEvents(@RequestBody Events events, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (events != null) {
            Boolean result = eventsService.deleteEvents(events.getEventId());
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
