package com.abiodunelijah.category.controllers;

import com.abiodunelijah.category.entities.Category;
import com.abiodunelijah.category.services.CategoryService;
import com.abiodunelijah.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/allCategories")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new ApiResponse("Successful", categories));
    }

    @PostMapping("/addCAtegory")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        Category addedCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(new ApiResponse("Category Added successfully", addedCategory));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        Category categoryById = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(new ApiResponse("category found", categoryById));
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<ApiResponse> findCategoryByName(@PathVariable String categoryName) {
        Category categoryByName = categoryService.findCategoryByName(categoryName);
        return ResponseEntity.ok(new ApiResponse("Found", categoryByName));
    }

    @PutMapping("/{categoryId}/")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        Category updatedCategory = categoryService.updateCategory(category, categoryId);
        return ResponseEntity.ok(new ApiResponse("found name", updatedCategory));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse("deleted successfully", "successful"));
    }

}
