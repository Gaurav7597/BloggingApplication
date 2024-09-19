package org.BloggingApplication.blog.repositories;

import org.BloggingApplication.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category , Integer> {

}
