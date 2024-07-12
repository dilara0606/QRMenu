package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.CategoryDto;
import com.QRMenu.menu.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(Category category);

    List<CategoryDto> getCategories();

    void deleteCategory(Integer id);

    CategoryDto editCategory(Integer id, Category category);
}
