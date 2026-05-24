package com.abiodunelijah.cart.controllers;

import com.abiodunelijah.cart.dtos.CartItemDto;
import com.abiodunelijah.cart.entities.Cart;
import com.abiodunelijah.cart.entities.CartItem;
import com.abiodunelijah.cart.service.CartItemService;
import com.abiodunelijah.cart.service.CartService;
import com.abiodunelijah.mappers.CartMapper;
import com.abiodunelijah.response.ApiResponse;
import com.abiodunelijah.users.entities.User;
import com.abiodunelijah.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;
    private final UserService userService;
    private final CartMapper cartMapper;
    private final CartService cartService;


    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long productId, @RequestParam int quantity) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartService.initilaizeNewCartForUser(user);
        CartItem cartItem = cartItemService.addItemToCart(cart.getId(), productId, quantity);
        CartItemDto cartItemDto = cartMapper.convertToDto(cartItem);
        return ResponseEntity.ok(new ApiResponse("Item added successfully!", cartItemDto));
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,
                                                          @PathVariable Long itemId) {
        cartItemService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(new ApiResponse("Item removed successfully!", null));
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long cartId,
                                                      @PathVariable Long itemId,
                                                      @RequestParam int quantity) {

        cartItemService.updateItemQuantity(cartId, itemId, quantity);
        return ResponseEntity.ok(new ApiResponse("Item updated successfully!", null));
    }


}
