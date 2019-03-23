package com.hobbymatcher.service;

import com.hobbymatcher.entity.Events;

import java.util.List;

public interface EventService {
    List<Events> getEventList();

    boolean addEvents(Events events);

    boolean joinEvents(String name);

}
