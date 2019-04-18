package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Comment;

import java.util.List;

public interface CommentDao {

    int addComment(Comment comment);

    int deleteComment(int id);

    List<Comment> listCommentByBlogId(int blogId);

}
