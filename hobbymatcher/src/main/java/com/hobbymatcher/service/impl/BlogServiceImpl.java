package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.BlogDao;
import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {


    private final BlogDao blogDao;

    public BlogServiceImpl(BlogDao blogDao) {
        this.blogDao = blogDao;
    }


    @Override
    public List<Blog> listBlog() {
        return blogDao.listBlog();
    }

    @Override
    public Boolean deleteBlog(int id) {
        try {
            return blogDao.deleteBlog(id) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean addBlog(Blog blog) {
        try {
            return blogDao.addBlog(blog) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Blog findBlogById(int id) {
        return blogDao.findBlogById(id);
    }

    @Override
    public Boolean updateBlog(Blog blog) {
        try {
            return blogDao.updateBlog(blog) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
