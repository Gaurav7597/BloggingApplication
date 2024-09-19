package org.BloggingApplication.blog.controllers;


import org.BloggingApplication.blog.payloads.ApiResponse;
import org.BloggingApplication.blog.payloads.CommentDto;
import org.BloggingApplication.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable Integer postId)
    {
        CommentDto createComment = this.commentService.createComment(commentDto, postId);

        return new ResponseEntity<CommentDto>(createComment , HttpStatus.CREATED);
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
    {
        this.commentService.DeleteComment(commentId);
        return new ResponseEntity<ApiResponse>( new ApiResponse("Comment Deleted Successfully !!" , true), HttpStatus.OK);
    }


}
