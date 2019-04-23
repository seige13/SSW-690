package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {

    int addComment(Comment comment);

    int deleteComment(@Param("id") int id);

    List<Comment> listCommentByBlogId(@Param("blogId") int blogId);

}
