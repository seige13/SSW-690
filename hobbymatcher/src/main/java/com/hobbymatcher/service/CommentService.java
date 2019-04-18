package com.hobbymatcher.service;

import com.hobbymatcher.entity.Blog;
import com.hobbymatcher.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentsByBlogId(int blogId);

    Boolean deleteComment(int id);

    Boolean addComment(Comment comment);

}
