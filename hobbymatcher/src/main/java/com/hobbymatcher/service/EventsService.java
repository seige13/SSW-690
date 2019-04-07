package com.hobbymatcher.service;

import com.hobbymatcher.entity.Events;

import java.awt.*;
import java.util.List;

public interface EventsService {
    List<Events> getEventsList();

    boolean addEvents(Events events);

    Events findEventsByTitle(String name);

    Events findEventsById(int id);

    boolean joinEvents(String id, String eventsId);

    boolean deleteEvents(String id);
}
