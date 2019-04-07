package com.hobbymatcher.entity;

import java.time.LocalDateTime;

public class Events {

    private String eventsId;
    private String eventsTitle;
    private LocalDateTime eventsTime;
    private String location;
    private String description;
    private String fee;
    private String holder;
    private String eventsImage;
    private String hobbyId;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getHobbyId() {
        return hobbyId;
    }


    public String geteventsId() {
        return eventsId;
    }

    public void setEventId(String eventsId) {
        this.eventsId = eventsId;
    }

    public String getEventsTitle() {
        return eventsTitle;
    }

    public void setEventsTitle(String eventsTitle) {
        this.eventsTitle = eventsTitle;
    }

    public LocalDateTime getEventsTime() {
        return eventsTime;
    }

    public void setEventsTime(LocalDateTime eventsTime) {
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setEventsImage(String eventsImage)
    {
        this.eventsImage = eventsImage;
    }

    public String getEventsImage() {
        return eventsImage;
    }
}
