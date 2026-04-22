package com.abiodunelijah.cart.repositories;

import com.abiodunelijah.cart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
