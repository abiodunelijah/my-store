package com.abiodunelijah.product.controllers;

import com.abiodunelijah.mappers.ProductMapper;
import com.abiodunelijah.product.dtos.AddProductRequest;
import com.abiodunelijah.product.dtos.ProductDto;
import com.abiodunelijah.product.dtos.UpdateProductRequest;
import com.abiodunelijah.product.entities.Product;
import com.abiodunelijah.product.services.ProductService;
import com.abiodunelijah.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Products found", convertedProducts));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        ProductDto productDto = productMapper.convertToDto(product);
        return ResponseEntity.ok(new ApiResponse("Found.", productDto));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        Product theProduct = productService.addProduct(product);
        ProductDto productDto = productMapper.convertToDto(theProduct);
        return ResponseEntity.ok(new ApiResponse("Add product success!", productDto));
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable Long productId) {
        Product theProduct = productService.updateProduct(request, productId);
        ProductDto productDto = productMapper.convertToDto(theProduct);
        return ResponseEntity.ok(new ApiResponse("Update product success!", productDto));
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(new ApiResponse("Delete product success!", productId));
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/{category}/products")
    public ResponseEntity<ApiResponse> findProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<ApiResponse> findProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        List<ProductDto> convertedProducts = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/distinct/products")
    public ResponseEntity<ApiResponse> getDistinctProductsByName() {
        List<Product> products = productService.findDistinctProductsByName();
        List<ProductDto> productDtos = productMapper.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", productDtos));
    }

    @GetMapping("/distinct/brands")
    public ResponseEntity<ApiResponse> getDistinctBrands() {
        return ResponseEntity.ok(new ApiResponse("Found", productService.getAllDistinctBrands()));
    }

}
