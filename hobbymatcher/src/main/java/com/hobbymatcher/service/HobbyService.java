package com.hobbymatcher.service;

import com.hobbymatcher.entity.Hobby;

import java.util.List;

public interface HobbyService {
    List<Hobby> listHobby();

    boolean deleteHobby(String id);

    boolean updateHobby(Hobby hobby);

    Hobby findHobbyByName(String name);

    Hobby findHobbyById(String id);

    boolean insertHobby(Hobby hobby);
}
