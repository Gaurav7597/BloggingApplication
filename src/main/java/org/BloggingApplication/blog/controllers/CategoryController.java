package org.BloggingApplication.blog.controllers;

import jakarta.validation.Valid;
import org.BloggingApplication.blog.payloads.ApiResponse;
import org.BloggingApplication.blog.payloads.CategoryDto;
import org.BloggingApplication.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto CreateCategoryDto = this.categoryService.createCategory(categoryDto);
        return  new ResponseEntity<>(CreateCategoryDto , HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer categoryId)
    {
        CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto , categoryId);
        return  ResponseEntity.ok(updateCategoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer catId)
    {
        this.categoryService.deleteCategory(catId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully" , true) , HttpStatus.OK);
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer catId)
    {
        this.categoryService.getCategory(catId);
        return  ResponseEntity.ok(this.categoryService.getCategory(catId));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory()
    {
        return  ResponseEntity.ok(this.categoryService.getAllCategory());
    }









}
