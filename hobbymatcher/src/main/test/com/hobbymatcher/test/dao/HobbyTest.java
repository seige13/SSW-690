package com.hobbymatcher.test.dao;

import com.hobbymatcher.dao.HobbyDao;
import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.EventsService;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author HaoxuanLi  Github:bestksl
 * @version created date：2019-05-08 14:27
 */
public class HobbyTest extends BaseTest {

    @Autowired
    private HobbyDao hobbyDao;

    @Test
    public void testQueryHobby() {
        List<Hobby> hobbyList = hobbyDao.queryHobby();
        assertNotEquals(0, hobbyList.size());
    }

    @Test
    public void testInsertHobby() {
        Hobby hobby = new Hobby();
        hobby.setDescription("test");
        hobby.setConstrains("test");
        hobby.setName("test1"+ UUID.randomUUID());
        int result = hobbyDao.insertHobby(hobby);
        assertEquals(1, result);

    }

    @Test
    public void testFindHobbyById() {
        Hobby hobby = hobbyDao.findHobbyById(1);
        assertNotNull(hobby);
    }


}
