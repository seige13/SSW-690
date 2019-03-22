package com.hobbymatcher.dao;

import com.hobbymatcher.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 列出user列表
     *
     * @return userList
     */
    List<User> queryUser();

    int insertUser(User user);

    User findUserByEmailAndPwd(@Param("email") String email, @Param("passWord") String passWord);

    User findUserById(@Param("id") String id);

    int updateUser(User user);

    int deleteUser(@Param("id") String id);

    User findUserByEmail(@Param("email") String email);

}