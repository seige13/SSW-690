package com.hobbymatcher.service;

import com.hobbymatcher.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    boolean regist(User user);

    boolean login(String email, String passWord);

    boolean deleteUser(String id);
}
