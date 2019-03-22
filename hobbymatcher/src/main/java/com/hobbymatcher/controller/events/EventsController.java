package com.hobbymatcher.controller.events;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
    public boolean add(String eventsTitle, Date eventsTime, String location, String description, String fee, String holder) {
        Events events = new Events();
		events.setEventsTitle(eventsTitle);
		events.setEventsTime(eventsTime);
		events.setLocation(location);
		events.setDescription(description);
		events.setFee(fee);
		events.setHolder(holder);
        return eventsService.addEvents(events);
    }

}
