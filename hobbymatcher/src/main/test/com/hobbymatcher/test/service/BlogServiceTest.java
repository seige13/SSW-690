package com.hobbymatcher.test.service;

import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.BlogService;
import com.hobbymatcher.service.EventsService;
import com.hobbymatcher.service.HobbyService;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Service;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class BlogServiceTest extends BaseTest {
    @Autowired
    private BlogService blogService;

    @Test
    public void testGetBlogList() {
        List<Blog> blogList = blogService.listBlog();
        assertNotEquals(0, blogList.size());
    }

    @Test
    public void testAddBlog() {
        Blog blog = new Blog();
        blog.setUserId(3);
        blog.setHobbyId(1);
        blog.setTitle("That is great");
        blog.setContent("really enjoy it!");
        blog.setCreateDate("2019-05-01 06:06:06");
        Boolean result = blogService.addBlog(blog);
        assertTrue(blogService.addBlog(blog));
    }

}
