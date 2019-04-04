package com.hobbymatcher.controller.event;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@SessionAttributes()
@Controller
@RequestMapping("/events")
public class EventController {

    private final EventsService eventsService;

    @Autowired
    public EventController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @RequestMapping(value = "/listevent", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listEvent(HttpServletResponse response) {
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

    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addevent(@RequestBody Events events, MultipartFile imageFile, HttpServletResponse response) throws IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (events == null || imageFile == null) {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        } else {
            String filePath = "webapp" + File.separator + "resources" + File.separator + "image";
            String originalFilename = imageFile.getOriginalFilename();
            String newFileName = UUID.randomUUID() + originalFilename;
            File targetFile = new File(filePath, newFileName);
            imageFile.transferTo(targetFile);
            events.setEventsImage(newFileName);
            Boolean result = eventsService.addEvents(events);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        }
    }

    @RequestMapping(value = "/deleteevent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteEvent(@RequestBody Events events, HttpServletResponse response) {
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
