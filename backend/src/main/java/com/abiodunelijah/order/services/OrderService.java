package com.abiodunelijah.order.services;

import com.abiodunelijah.order.dtos.OrderDto;
import com.abiodunelijah.order.entities.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);

    List<OrderDto> getUserOrders(Long userId);


//    String createPaymentIntent(PaymentRequest request) throws StripeException;
//
//    OrderDto convertToDto(Order order);

}
