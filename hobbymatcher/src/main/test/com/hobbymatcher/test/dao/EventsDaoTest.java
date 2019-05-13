package com.hobbymatcher.test.dao;

import com.hobbymatcher.dao.EventsDao;
import com.hobbymatcher.entity.Events;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventsDaoTest extends BaseTest {

    @Autowired
    private EventsDao eventsDao;

//    @Test
//    public void testQueryEvents() {
//        List<Events> eventsList = eventsDao.queryEvents();
//        assertNotEquals(0, eventsList.size());
//    }

    @Test
    public void testInsertEvents() {
        Events events = new Events();
        events.setEventsTitle("Play Basketball!");
        events.setEventsTime("2020-05-08 14:21:13");
        events.setDescription("NBA");
        events.setLocation("howe");
        events.setFee("100");
        events.setHolder("Zhe");
        int result = eventsDao.addEvents(events);
        assertEquals(1, result);

    }

    @Test
    public void testJoinEvents() {
        int result=eventsDao.joinEvents(3 + "", 1+"");
        assertEquals(1,result);
    }

}
