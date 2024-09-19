package org.BloggingApplication.blog.repositories;

import org.BloggingApplication.blog.entities.Category;
import org.BloggingApplication.blog.entities.Post;
import org.BloggingApplication.blog.entities.User;
import org.BloggingApplication.blog.payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post , Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);

//    List<Post> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);









}



