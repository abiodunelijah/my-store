package com.abiodunelijah.order.controllers;

import com.abiodunelijah.mappers.OrderMapper;
import com.abiodunelijah.order.dtos.OrderDto;
import com.abiodunelijah.order.entities.Order;
import com.abiodunelijah.order.services.OrderService;
import com.abiodunelijah.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/user/{userId}/place-order")
    public ResponseEntity<ApiResponse> placeOrder(@PathVariable Long userId) {
        Order order = orderService.placeOrder(userId);
        OrderDto orderDto = orderMapper.convertToDto(order);
        return ResponseEntity.ok(new ApiResponse("Order placed successfully!", orderDto));
    }

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId) {
        List<OrderDto> orders = orderService.getUserOrders(userId);
        System.out.println("The orders : ============================" + Arrays.toString(orders.toArray()));
        return ResponseEntity.ok(new ApiResponse("Success!", orders));
    }

//    @PostMapping("/create-payment-intent")
//    public ResponseEntity<?> createPaymentIntent(@RequestBody PaymentRequest request) throws StripeException {
//        String clientSecret = orderService.createPaymentIntent(request);
//        return ResponseEntity.ok(Map.of("clientSecret", clientSecret));
//    }
}
