package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUserList() {
        return userDao.queryUser();
    }

    @Override
    public boolean regist(User user) {
        try {
            userDao.insertUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean login(String email, String passWord) {
        User user = userDao.findUserByEmailAndPwd(email, passWord);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String id) {
        try {
            userDao.deleteUser(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
