package com.hobbymatcher.util;

import com.hobbymatcher.entity.Events;

import java.util.List;

public class EventsUtil {
    public static List<Events> changeTime(List<Events> eventsList) {
        try {
            for (Events e : eventsList
            ) {
                e.setFrontendTime(e.getEventsTime().toString());
            }
        } catch (Exception e) {
            return null;
        }
        return eventsList;

    }

    public static Events changeOneTime(Events e) {
        try {
            e.setFrontendTime(e.getEventsTime().toString());
        } catch (Exception a) {
            return null;
        }
        return e;

    }
}
