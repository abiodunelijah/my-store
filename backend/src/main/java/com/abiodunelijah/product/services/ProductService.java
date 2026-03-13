package com.abiodunelijah.product.services;

import com.abiodunelijah.product.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    Product updateProduct(Product product, Long productId);

    Product getProductById(Long productId);

    void deleteProductById(Long productId);


    List<Product> getAllProducts();

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByBrandAndName(String brand, String name);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByName(String name);


}
