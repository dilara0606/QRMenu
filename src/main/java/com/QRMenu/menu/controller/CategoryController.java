package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.CategoryDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create-category")
    public CategoryDto createCategory(@RequestBody Category category) {
       return categoryService.saveCategory(category);
    }
    
    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/edit-category/{id}")
    public CategoryDto editCategory(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.editCategory(id,category);
    }
}
