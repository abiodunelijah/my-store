package com.abiodunelijah.product.controllers;

import com.abiodunelijah.mappers.ProductMapper;
import com.abiodunelijah.product.dtos.ProductDto;
import com.abiodunelijah.product.entities.Product;
import com.abiodunelijah.product.services.ProductService;
import com.abiodunelijah.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Products", convertedProducts));
    }


}
