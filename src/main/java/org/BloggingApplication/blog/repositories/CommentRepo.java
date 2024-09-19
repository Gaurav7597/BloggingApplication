package org.BloggingApplication.blog.repositories;

import org.BloggingApplication.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment , Integer> {


}
