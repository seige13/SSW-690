package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.CommentDao;
import com.hobbymatcher.entity.Comment;
import com.hobbymatcher.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> listCommentsByBlogId(int blogId) {
        List<Comment> commentList = new ArrayList<Comment>();
        try {
            commentList = commentDao.listCommentByBlogId(blogId);
            return commentList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public Boolean deleteComment(int id) {
        try {
            if (commentDao.deleteComment(id) == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean addComment(Comment comment) {
        if (comment == null) {
            return false;
        } else {
            return commentDao.addComment(comment) != 0;
        }
    }

    @Override
    public List<Comment> listCommentsByUserId(int userId) {
        return commentDao.listCommentByUserId(userId);
    }
}
