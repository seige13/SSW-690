package com.hobbymatcher.controller.events;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private Map<String, Object> listEvents() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Events> list = new ArrayList<Events>();
        try {
            list = eventsService.getEventsList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    @RequestMapping(value = "/addevents", method = RequestMethod.POST)
    @ResponseBody
    public boolean add(@RequestBody Events events, MultipartFile imageFile) throws IOException {
        if (events == null) {
            return false;
        }
        String filePath = "webapp" + File.separator + "resources" + File.separator + "image";
        String originalFilename = imageFile.getOriginalFilename();
        String newFileName = UUID.randomUUID() + originalFilename;
        File targetFile = new File(filePath, newFileName);
        imageFile.transferTo(targetFile);
        events.setEventsImage(newFileName);
        return eventsService.addEvents(events);
    }

}
