package org.BloggingApplication.blog.services.impl;

import org.BloggingApplication.blog.entities.Category;
import org.BloggingApplication.blog.exceptions.ResourceNotFoundException;
import org.BloggingApplication.blog.payloads.CategoryDto;
import org.BloggingApplication.blog.repositories.CategoryRepo;
import org.BloggingApplication.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = this.modelMapper.map(categoryDto , Category.class);
        Category addedCat = this.categoryRepo.save(category);
        return this.modelMapper.map(addedCat , CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","Category Id",categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCat = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCat , CategoryDto.class );
    }

    @Override
    public void deleteCategory(Integer categoryId) {
            Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));

            this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));

        return this.modelMapper.map(category , CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.modelMapper.map(category , CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
