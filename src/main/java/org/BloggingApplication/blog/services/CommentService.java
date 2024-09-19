package org.BloggingApplication.blog.services;

import org.BloggingApplication.blog.payloads.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto , Integer postId);
    void DeleteComment(Integer commentId);
}
