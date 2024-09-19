package org.BloggingApplication.blog.services;

import org.BloggingApplication.blog.entities.Post;
import org.BloggingApplication.blog.payloads.PostDto;
import org.BloggingApplication.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //Create
    PostDto createPost(PostDto postDto, Integer userId , Integer categoryId);
    //update
    PostDto updatePost(PostDto postDto, Integer postId);
    //delete
    void deletePost(Integer postId);

    //get
    PostDto getPostById(Integer postId);

    PostResponse getAllPost(Integer pageNo , Integer pageSize , String sortBy, String sortDir);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}


