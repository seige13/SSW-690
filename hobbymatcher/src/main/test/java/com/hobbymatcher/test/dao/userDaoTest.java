package com.hobbymatcher.test.dao;

import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class userDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testQueryUser() {
        List<User> userList = userDao.queryUser();
        assertEquals(2, userList.size());
    }



}
