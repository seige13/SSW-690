package com.hobbymatcher.test.service;

import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserList() {
        List<User> userList = userService.getUserList();
        assertEquals("robert", userList.get(1).getNickName());
    }

}
