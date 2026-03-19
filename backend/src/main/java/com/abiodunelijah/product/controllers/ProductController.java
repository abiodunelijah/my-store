package com.abiodunelijah.product.controllers;

import com.abiodunelijah.product.entities.Product;
import com.abiodunelijah.product.services.ProductService;
import com.abiodunelijah.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Products", products));
    }
    

}
