package com.hobbymatcher.service;

import com.hobbymatcher.entity.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> listBlog();

    Boolean deleteBlog(int id);

    Boolean addBlog(Blog blog);

    Blog findBlogById(int id);

    Boolean updateBlog(Blog blog);

    List<Blog> listBlogByHobbyId(int id);
}
