package com.hobbymatcher.test.dao;

import com.hobbymatcher.dao.BlogDao;
import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author HaoxuanLi  Github:bestksl
 * @version created date：2019-05-08 15:19
 */
public class BlogTest extends BaseTest {
    @Autowired
    private BlogDao blogDao;

    @Test
    public void testQueryBlogs() {
        List<Blog> eventsList = blogDao.listBlog();
        assertNotEquals(0, eventsList.size());
    }

    @Test
    public void testInsertBlog() {
        Blog blog = new Blog();
        blog.setContent("test");
        blog.setTitle("test");
        blog.setHobbyId(1);
        blog.setUserId(6);
        int result = blogDao.addBlog(blog);
        assertEquals(1, result);

    }



}
