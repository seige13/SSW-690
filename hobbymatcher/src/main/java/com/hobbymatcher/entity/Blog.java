package com.hobbymatcher.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Blog implements Serializable {
    private String blogId;
    private String title;
    private String content;
    private int hobbyId;
    private int userId;
    private Timestamp createDate;
    private String frontEndCreateTime;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(int hobbyId) {
        this.hobbyId = hobbyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getFrontEndCreateTime() {
        return frontEndCreateTime;
    }

    public void setFrontEndCreateTime(String frontEndCreateTime) {
        this.frontEndCreateTime = frontEndCreateTime;
    }
}
