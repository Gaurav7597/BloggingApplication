package org.BloggingApplication.blog.payloads;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.BloggingApplication.blog.entities.Category;
import org.BloggingApplication.blog.entities.Comment;
import org.BloggingApplication.blog.entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
        private int postId;
        private String Title;
        private String Content;
        private String ImageName;
        private Date addedDate;
        private CategoryDto category;
        private UserDto user;
        private Set<CommentDto> comments = new HashSet<>();


}
