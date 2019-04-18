package com.hobbymatcher.controller.blog;

import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.entity.Comment;
import com.hobbymatcher.service.BlogService;
import com.hobbymatcher.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {


    private final BlogService blogService;
    private final CommentService commentService;

    public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    //list Blog
    @RequestMapping(value = "/listblog", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listBlog(HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("list", blogService.listBlog());
        response.setStatus(200);
        return modelMap;
    }

    //deleteBlog
    @RequestMapping(value = "/deleteblog", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteBlog(String id, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            int id1 = Integer.parseInt(id);
            Boolean result = blogService.deleteBlog(id1);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
        } catch (Exception e) {
            modelMap.put("msg", "valueError");
            modelMap.put("status", false);
            response.setStatus(400);
        }
        return modelMap;
    }


    //addBlog
    @RequestMapping(value = "/createblog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createBlog(@RequestBody Blog blog, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (blog != null) {
            Boolean result = blogService.addBlog(blog);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        } else {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }

    }

    //addBlog
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addcomment(@RequestBody Comment comment, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (comment != null) {
            Boolean result = commentService.addComment(comment);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        } else {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }

    }


    //findBlogById
    //update blog
    @RequestMapping(value = "/findblogbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findBlogById(String blogId, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            int id1 = Integer.parseInt(blogId);
            Blog blog = blogService.findBlogById(id1);
            if (blog == null) {
                modelMap.put("status", false);
                modelMap.put("msg", "no target blog");
                response.setStatus(400);
                return modelMap;
            }
            modelMap.put("blog", blog);
            response.setStatus(200);
            return modelMap;
        } catch (Exception e) {
            modelMap.put("msg", "valueError");
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }
    }

    @RequestMapping(value = "/updateblog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateBlog(@RequestBody Blog blog, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (blog != null) {
            Boolean result = blogService.updateBlog(blog);
            modelMap.put("status", result);
            response.setStatus(result ? 200 : 400);
            return modelMap;
        } else {
            modelMap.put("status", false);
            response.setStatus(400);
            return modelMap;
        }

    }


}