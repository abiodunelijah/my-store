package com.abiodunelijah.mappers;

import com.abiodunelijah.cart.dtos.CartDto;
import com.abiodunelijah.cart.dtos.CartItemDto;
import com.abiodunelijah.cart.entities.Cart;
import com.abiodunelijah.cart.entities.CartItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final ModelMapper modelMapper;

    public CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

    public CartItemDto convertToDto(CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemDto.class);
    }


}
