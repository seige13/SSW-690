package com.hobbymatcher.test.dao;

import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    //@Test
    public void testQueryUser() {
        List<User> userList = userDao.queryUser();
        assertEquals(6, userList.size());
    }

    //@Test
    public void testInsertUser() {
        User user = new User();
        user.setEmail("kslup7777@gmail.com");
        user.setFirstName("fi");
        user.setLastName("la");
        user.setNickName("ksl");
        user.setPassWord("123456");
        int result = userDao.insertUser(user);
        assertEquals(0, result);
    }

    // @Test
    public void testFindUserByEmailAndPwd() {
        User user = userDao.findUserByEmailAndPwd("kslup7@gmail.com", "123456");
        assertNotNull(user);
    }

    //  @Test
    public void testFindUserById() {
        User user = userDao.findUserById("1");
        assertNotNull(user);
    }

    // @Test
    public void testUpdateUser() {
        User user = userDao.findUserByEmailAndPwd("kslup7@gmail.com", "123456");
        user.setPassWord("123");
        userDao.updateUser(user);
        user = userDao.findUserById(user.getId());
        assertEquals("123", user.getPassWord());
    }


}