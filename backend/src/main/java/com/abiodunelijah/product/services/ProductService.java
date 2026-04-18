package com.abiodunelijah.product.services;

import com.abiodunelijah.product.dtos.AddProductRequest;
import com.abiodunelijah.product.dtos.UpdateProductRequest;
import com.abiodunelijah.product.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest request);

    Product updateProduct(UpdateProductRequest request, Long productId);

    Product getProductById(Long productId);

    void deleteProductById(Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByBrandAndName(String brand, String name);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByName(String name);


    List<Product> findDistinctProductsByName();

    List<Product> getProductsByCategoryId(Long categoryId);

    Object getAllDistinctBrands();
}
