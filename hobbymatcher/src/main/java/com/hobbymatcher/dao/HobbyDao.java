package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Hobby;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HobbyDao {
    List<Hobby> queryHobby();

    int insertHobby(Hobby hobby);

    Hobby findHobbyByName(@Param("name") String name);

    Hobby findHobbyById(@Param("id") int id);

    int updateHobby(Hobby hobby);

    int deleteHobby(@Param("id") int id);

}
