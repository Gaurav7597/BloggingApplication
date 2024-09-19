package org.BloggingApplication.blog.services;

import org.BloggingApplication.blog.payloads.CategoryDto;
import org.BloggingApplication.blog.payloads.UserDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //get
    CategoryDto getCategory(Integer categoryId);

    //get_all
    List<CategoryDto> getAllCategory();

}
