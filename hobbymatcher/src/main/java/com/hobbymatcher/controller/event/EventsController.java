package com.hobbymatcher.controller.event;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.EventsService;
import com.hobbymatcher.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.time.LocalDateTime;

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
    public Map<String, Object> listEvents(String id, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Events> list = new ArrayList<Events>();
        try {
            if (!"".equals(id)&&id!=null)
            {
                modelMap.put("list", eventsService.listEventsByHobbyId(Integer.parseInt(id)));
                modelMap.put("msg", "list with hobby success");
                response.setStatus(200);
                return modelMap;
            }else {
                list = eventsService.getEventsList();
                modelMap.put("list", list);
                modelMap.put("status", true);
                response.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("status", false);
            modelMap.put("msg", e.getMessage());
            response.setStatus(400);
        }
        return modelMap;
    }

    @RequestMapping(value = "/addevents", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestPart("events") Events events, @RequestPart("file") MultipartFile imageFile, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (events != null) {
            if (imageFile != null) {
                String imgPath = FileUtil.transferFile(imageFile);
                events.setEventsImage(imgPath);
            }
            Boolean result = eventsService.addEvents(events);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        } else {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }
    }

    @RequestMapping(value = "/joinevents", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> joinEvents(String id, @RequestParam(value = "events_id") String eventsId, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            List<Events> list = new ArrayList<Events>();
            List<String> num = new ArrayList<>();
            list = eventsService.getEventsForUser(id);
            for(int i = 0;i < list.size();i++)
            {
                num.add(list.get(i).getEventsId());
            }
            if(num.contains(eventsId))
            {
                modelMap.put("msg", "User already join this event");
                modelMap.put("status", false);
                response.setStatus(400);
            }
            else {
                Boolean result = eventsService.joinEvents(id, eventsId);
                modelMap.put("status", result);
                response.setStatus(result ? 200 : 400);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            modelMap.put("msg", "valueError");
            modelMap.put("status", false);
            response.setStatus(400);
        }
        return modelMap;
    }

    @RequestMapping(value = "/findpastevents", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findPastEvents(String id, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Events> list = new ArrayList<Events>();
        try {
            //User user = (User) request.getSession().getAttribute("user");
            //String userId = user.getId();
            LocalDateTime timeStamp = LocalDateTime.now();
            list = eventsService.findPastEvents(id, timeStamp);
            modelMap.put("list", list);
            response.setStatus(list == null ? 400 : 200);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("msg", "valueError");
            modelMap.put("status", false);
            response.setStatus(400);
        }
        return modelMap;
    }

    @RequestMapping(value = "/findupcomingevents", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findUpcomingEvents(String id, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Events> list = new ArrayList<Events>();
        try {
            //User user = (User) request.getSession().getAttribute("user");
            //String userId = user.getId();
            LocalDateTime timeStamp = LocalDateTime.now();
            list = eventsService.findUpcomingEvents(id, timeStamp);
            modelMap.put("events", list);
        } catch (Exception e) {
            System.out.println(e.toString());
            modelMap.put("msg", "valueError");
            modelMap.put("status", false);
            response.setStatus(400);
        }
        return modelMap;
    }

    @RequestMapping(value = "/geteventsforuser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEventsForUser(String id, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Events> list = new ArrayList<Events>();
        try {
            //User user = (User) request.getSession().getAttribute("user");
            //String userId = user.getId();
            list = eventsService.getEventsForUser(id);
            modelMap.put("events", list);
        } catch (Exception e) {
            System.out.println(e.toString());
            modelMap.put("msg", "valueError");
            modelMap.put("status", false);
            response.setStatus(400);
        }
        return modelMap;
    }

    @RequestMapping(value = "/getevents", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEvents(@RequestParam(value = "events_id") String id, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            int id1 = Integer.parseInt(id);
            System.out.println(id1);
            Events eventsById = eventsService.findEventsById(id1);
            int number = eventsService.getNumber(id1);
            modelMap.put("events", eventsById);
            modelMap.put("numberOfUser", number);
            response.setStatus(eventsById == null ? 400 : 200);
        } catch (Exception e) {
            System.out.println(e.toString());
            modelMap.put("msg", "valueError");
            modelMap.put("status", false);
            response.setStatus(400);
        }
        return modelMap;
    }

    @RequestMapping(value = "/deleteevents", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteEvents(@RequestBody Events events, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (events != null) {
            Boolean result = eventsService.deleteEvents(events.getEventsId());
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
