package com.hobbymatcher.service;

import com.hobbymatcher.entity.Events;

import java.util.List;

public interface EventsService {
    List<Events> getEventsList();

    boolean addEvents(Events events);


}
