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
    private String frontendTime;

    public String getFrontendTime() {
        return frontendTime;
    }

    public void setFrontendTime(String frontendTime) {
        this.frontendTime = frontendTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
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
        return eventsTime;
    }

    public void setEventsTime(LocalDateTime eventsTime) {
        this.eventsTime = eventsTime;
    }

    public void setEventsTime(String eventsTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

    public String getEventsImage() {
        return eventsImage;
    }

    public void setEventsImage(String eventsImage) {
        this.eventsImage = eventsImage;
    }
}