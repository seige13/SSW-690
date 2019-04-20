package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Events;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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

    List<Events> findPastEvents(@Param("id")String id, @Param("currentTime") LocalDateTime currentTime);

    List<Events> findUpcomingEvents(@Param("id")String id, @Param("currentTime") LocalDateTime currentTime);

    int deleteEvents(@Param("id") String id);

    int updateEvents(Events events);
}