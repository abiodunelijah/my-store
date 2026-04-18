package com.abiodunelijah.product.services.impl;

import com.abiodunelijah.cart.entities.Cart;
import com.abiodunelijah.cart.entities.CartItem;
import com.abiodunelijah.cart.repositoties.CartItemRepository;
import com.abiodunelijah.category.entities.Category;
import com.abiodunelijah.category.repositories.CategoryRepository;
import com.abiodunelijah.order.entities.OrderItem;
import com.abiodunelijah.order.repositories.OrderItemRepository;
import com.abiodunelijah.product.dtos.AddProductRequest;
import com.abiodunelijah.product.dtos.UpdateProductRequest;
import com.abiodunelijah.product.entities.Product;
import com.abiodunelijah.product.repositories.ProductRepository;
import com.abiodunelijah.product.services.ProductService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Product addProduct(AddProductRequest request) {

        if (productExists(request.getName(), request.getBrand())) {
            throw new EntityExistsException(request.getName() + " already exist.");
        }

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);

        return productRepository.save(createProduct(request, category));
    }

    private boolean productExists(String brand, String name) {
        return productRepository.existsByBrandAndName(brand, name);
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category);
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {

        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found."));
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.findById(productId).ifPresentOrElse(product -> {
            List<CartItem> cartItems = cartItemRepository.findByProductId(productId);
            cartItems.forEach(cartItem -> {
                Cart cart = cartItem.getCart();
                cart.removeItem(cartItem);
                cartItemRepository.delete(cartItem);
            });

            List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
            orderItems.forEach(orderItem -> {
                orderItem.setProduct(null);
                orderItemRepository.save(orderItem);
            });

            Optional.ofNullable(product.getCategory()).ifPresent(category -> category.getProducts().remove(product));
            product.setCategory(null);
            productRepository.deleteById(product.getId());
        }, () -> {
            throw new EntityNotFoundException("Product not found");
        });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findDistinctProductsByName() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return List.of();
    }

    @Override
    public Object getAllDistinctBrands() {
        return null;
    }
}
