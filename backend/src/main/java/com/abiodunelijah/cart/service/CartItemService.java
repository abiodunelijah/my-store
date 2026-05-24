package com.abiodunelijah.cart.service;

import com.abiodunelijah.cart.entities.CartItem;

public interface CartItemService {

    CartItem addItemToCart(Long cartId, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartItem, Long productId);

}
