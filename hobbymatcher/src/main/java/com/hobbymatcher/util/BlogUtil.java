package com.hobbymatcher.util;

import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.entity.Events;

import java.util.List;

public class BlogUtil {
    public static List<Blog> changeTime(List<Blog> blogsList) {
        try {
            for (Blog e : blogsList
            ) {
                e.setFrontEndCreateTime(e.getCreateDate().toString());
            }
        } catch (Exception e) {
            return null;
        }
        return blogsList;

    }

    public static Blog changeTime(Blog b) {
        try {
            b.setFrontEndCreateTime(b.getCreateDate().toString());
        } catch (Exception e) {
            return null;
        }
        return b;

    }
}
