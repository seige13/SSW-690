package com.hobbymatcher.test.service;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.service.EventsService;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EventsServiceTest extends BaseTest {
    @Autowired
    private EventsService eventsService;

    @Test
    public void testGetEventsList() {
        List<Events> eventsList = eventsService.getEventsList();
        assertEquals("wxs", eventsList.get(0).getHolder());
    }

}
