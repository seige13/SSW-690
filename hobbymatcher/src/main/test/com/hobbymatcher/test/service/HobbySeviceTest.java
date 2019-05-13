package com.hobbymatcher.test.service;

import com.hobbymatcher.entity.Events;
import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.EventsService;
import com.hobbymatcher.service.HobbyService;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Service;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author HaoxuanLi  Github:bestksl
 * @version created date：2019-05-08 15:31
 */
public class HobbySeviceTest extends BaseTest {
    @Autowired
    private HobbyService hobbyService;

    @Test
    public void testGetHobbyList() {
        List<Hobby> hobbyList = hobbyService.listHobby();
        assertNotEquals(0, hobbyList.size());
    }

//
//    @Test
//    public void testFindHobbyById() {
//        Hobby hobby = hobbyService.findHobbyById(1);
//        assertNotEquals(null, hobby);
//    }
//

    @Test
    public void testDeleteHobby() {
        //delete hobby by id in database
        assertEquals(false, hobbyService.deleteHobby(5));

    }
}
