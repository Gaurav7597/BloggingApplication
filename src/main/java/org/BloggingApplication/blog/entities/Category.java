package org.BloggingApplication.blog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId ;

    @Column(name = "title" , length = 100 , nullable = false)
    private String categoryTitle ;

    @Column(name = "Description" , length = 500)
    private String CategoryDescription ;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL  , fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();





}
