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

    int joinEvents(@Param("id")String id, @Param("eventsId")String eventsId);

	Events findEventsByTitle(@Param("eventsTitle") String title);

	Events findEventsById(@Param("events_id") int id);

    int deleteEvents(@Param("id") String id);

    int updateEvents(Events events);

}