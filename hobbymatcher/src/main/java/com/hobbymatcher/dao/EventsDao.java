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

    int joinEvents(@Param("id")String id, @Param("events_id")String eventsId);

	Events findEventsByTitle(@Param("eventsTitle") String title);

	Events findEventsById(@Param("id") int id);

    int deleteEvents(@Param("id") String id);

   // int joinEvents(@Param("id"))
}