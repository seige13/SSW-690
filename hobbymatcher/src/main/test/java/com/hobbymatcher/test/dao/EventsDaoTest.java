package com.hobbymatcher.test.dao;

import com.hobbymatcher.dao.EventsDao;
import com.hobbymatcher.entity.Events;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.time.LocalDateTime;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class EventsDaoTest extends BaseTest {

    @Autowired
    private EventsDao eventsDao;

    @Test
    public void testQueryEvents() {
        List<Events> eventsList = eventsDao.queryEvents();
        assertEquals(1, eventsList.size());
    }

    @Test
    public void testInsertEvents() {
        Events events = new Events();
        events.setEventsTitle("Play Basketball!");
        LocalDateTime date = LocalDateTime.now();
        events.setEventsTime(date);
        events.setDescription("NBA");
        events.setLocation("howe");
        events.setFee("100");
        events.setHolder("Zhe");
        int result = eventsDao.addEvents(events);
        assertEquals(0, result);

    }

}
