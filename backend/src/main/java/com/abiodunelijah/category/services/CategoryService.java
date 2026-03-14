package com.abiodunelijah.category.services;

import com.abiodunelijah.category.entities.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Category category, Long categoryId);

    void deleteCategory(Long categoryId);

    List<Category> getAllCategories();

    Category findCategoryByName(String name);

    Category findCategoryById(Long categoryId);
}
