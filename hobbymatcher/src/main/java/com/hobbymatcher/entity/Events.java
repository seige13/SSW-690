package com.hobbymatcher.entity;

import java.util.Date;

public class Events {
    
    private String eventsTitle;
    private Date eventsTime;
    private String location;
    private String description;
    private Int fee;
    private String holder;
    //private picture

    public String getEventsTitle() {
        return eventsTitle;
    }

    public void setEventsTitle(String eventsTitle) {
        this.eventsTitle = eventsTitle;
    }

    public String getEventsTime() {
        return eventsTime;
    }

    public void setEventsTime(Date eventsTime) {
        this.eventsTime = eventsTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Int getFee() {
        return fee;
    }

    public void setFee(Int fee) {
        this.fee = fee;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
