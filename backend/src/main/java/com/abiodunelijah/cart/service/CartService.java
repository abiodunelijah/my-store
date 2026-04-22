package com.abiodunelijah.cart.service;

import com.abiodunelijah.cart.entities.Cart;
import com.abiodunelijah.users.entities.User;

import java.math.BigDecimal;

public interface CartService {

    Cart getCart(Long cartId);

    Cart getCartByUserId(Long userId);

    void clearCart(Long cartId);

    Cart initilaizeNewCartForUser(User user);

    BigDecimal getTotalPrice(Long cartId);
}
