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

	int addEvents(Events event);

    int deleteEvents(@Param("id") String id);
}