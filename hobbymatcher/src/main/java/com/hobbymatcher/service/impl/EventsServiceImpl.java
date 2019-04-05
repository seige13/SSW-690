package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.EventsDao;
import com.hobbymatcher.entity.Events;
import com.hobbymatcher.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {

    private final EventsDao eventsDao;

    @Autowired
    public EventsServiceImpl(EventsDao eventsDao) {
        this.eventsDao = eventsDao;
    }

    @Override
    public List<Events> getEventsList() {
        return eventsDao.queryEvents();
    }

    @Override
    public boolean addEvents(Events event) {
        try {
            eventsDao.addEvents(event);
            return true;
        } catch (Exception e) {
            System.out.print(e.toString());
            return false;
        }
    }

    @Override
    public boolean deleteEvents(String id) {
        return eventsDao.deleteEvents(id) != 0;
    }
   
}
