package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.BlogDao;
import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {


    private final BlogDao blogDao;
    private final UserDao userDao;

    public BlogServiceImpl(BlogDao blogDao, UserDao userDao) {
        this.blogDao = blogDao;
        this.userDao = userDao;
    }


    @Override
    public List<Blog> listBlog() {
        List<Blog> blogs = blogDao.listBlog();
        if (blogs == null || blogs.size() == 0) {
            return null;
        }
        for (Blog b : blogs
        ) {
            b.setUser(userDao.findUserById(b.getUserId() + ""));
        }


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
        Blog blog = blogDao.findBlogById(id);
        blog.setUser(userDao.findUserById(blog.getUserId() + ""));
        return blog;
    }

    @Override
    public Boolean updateBlog(Blog blog) {
        try {
            return blogDao.updateBlog(blog) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Blog> listBlogByHobbyId(int id) {
        List<Blog> blogs = blogDao.listBlogByHobbyId(id);
        if (blogs == null || blogs.size() == 0) {
            return null;
        }
        for (Blog b : blogs
        ) {
            b.setUser(userDao.findUserById(b.getUserId() + ""));
        }
        return blogDao.listBlogByHobbyId(id);
    }
}
