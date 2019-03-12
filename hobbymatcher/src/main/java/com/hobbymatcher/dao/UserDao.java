package com.hobbymatcher.dao;

import com.hobbymatcher.entity.User;

import java.util.List;

public interface UserDao {
    /**
     * 列出user列表
     * @return userList
     */
    List<User> queryUser();
}
