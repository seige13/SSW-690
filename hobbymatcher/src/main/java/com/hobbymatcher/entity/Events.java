package com.hobbymatcher.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getEventsId() {
        return eventsId;
    }

    public void setEventsId(String eventsId) {
        this.eventsId = eventsId;
    }

    public String getEventsTitle() {
        return eventsTitle;
    }

    public void setEventsTitle(String eventsTitle) {
        this.eventsTitle = eventsTitle;
    }

    public LocalDateTime getEventsTime() {
    public String getEventsTime() {

        return this.eventsTime.toString();
    }

    public void setEventsTime(String eventsTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.from(f.parse(eventsTime));
        this.eventsTime = dateTime;
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