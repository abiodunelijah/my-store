package com.abiodunelijah.cart.controllers;

import com.abiodunelijah.cart.dtos.CartDto;
import com.abiodunelijah.cart.entities.Cart;
import com.abiodunelijah.cart.service.CartService;
import com.abiodunelijah.mappers.CartMapper;
import com.abiodunelijah.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @GetMapping("/user/{userId}/cart")
    public ResponseEntity<ApiResponse> getUserCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        CartDto cartDto = cartMapper.convertToDto(cart);
        return ResponseEntity.ok(new ApiResponse("Success", cartDto));
    }

    @DeleteMapping("/cart/{cartId}/clear")
    public void clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
    }

}
