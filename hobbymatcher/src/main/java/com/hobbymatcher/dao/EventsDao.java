package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Events;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventsDao {
    /**
     * 
     *
     * @return eventsList
     */
    List<Events> queryEvents();

    int insertEvents(Events event);

	int insertUserToEvents(String name);

}