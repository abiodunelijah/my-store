package com.abiodunelijah.mappers;

import com.abiodunelijah.order.dtos.OrderDto;
import com.abiodunelijah.order.entities.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

}
