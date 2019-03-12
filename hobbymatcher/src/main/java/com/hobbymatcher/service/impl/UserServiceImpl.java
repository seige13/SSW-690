package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList() {
        return userDao.queryUser();
    }
}
