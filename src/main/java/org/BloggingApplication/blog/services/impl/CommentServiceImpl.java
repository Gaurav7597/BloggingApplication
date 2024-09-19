package org.BloggingApplication.blog.services.impl;

import org.BloggingApplication.blog.entities.Comment;
import org.BloggingApplication.blog.entities.Post;
import org.BloggingApplication.blog.exceptions.ResourceNotFoundException;
import org.BloggingApplication.blog.payloads.CommentDto;
import org.BloggingApplication.blog.repositories.CommentRepo;
import org.BloggingApplication.blog.repositories.PostRepo;
import org.BloggingApplication.blog.repositories.UserRepo;
import org.BloggingApplication.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {



        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
        Comment comment = this.modelMapper.map(commentDto , Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment , CommentDto.class);
    }

    @Override
    public void DeleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","comment id",commentId));
        this.commentRepo.delete(comment);

    }
}
