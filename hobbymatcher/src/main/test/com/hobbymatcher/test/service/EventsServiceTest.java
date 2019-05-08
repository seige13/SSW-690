package com.hobbymatcher.test.service;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.service.EventsService;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;


public class EventsServiceTest extends BaseTest {
    @Autowired
    private EventsService eventsService;

    @Test
    public void testGetEventsList() {
        List<Events> eventsList = eventsService.getEventsList();
        assertNotEquals(0, eventsList.size());
    }

    @Test
    public void testGetEventsForUser() {
        List<Events> eventsList = eventsService.getEventsForUser("3");
        assertNotEquals(0, eventsList.size());
    }

    @Test
    public void testGetPastEvents() {
        LocalDateTime time = LocalDateTime.now();
        List<Events> eventsList = eventsService.findPastEvents("3", time);
        assertNotEquals(0, eventsList.size());
    }

    @Test
    public void testGetUpcomingEvents() {
        LocalDateTime time = LocalDateTime.now();
        List<Events> eventsList = eventsService.findUpcomingEvents("3", time);
        assertNotEquals(0, eventsList.size());
    }

    @Test
    public void testGetNumberForEvents() {
        //number of people join the events
        LocalDateTime time = LocalDateTime.now();
        int number = eventsService.getNumber(3);
        assertEquals(2, number);
    }
}
