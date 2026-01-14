package com.example.POS.Controller;

import com.example.POS.DTO.CreateOrderRequest;
import com.example.POS.Model.Order;
import com.example.POS.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest) {
     Order order = orderService.createOrder(orderRequest);
     return  ResponseEntity.ok().body(order);

    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<?> getOrders(@PathVariable String referenceId) {

        Order order = orderService.getOrder(referenceId);
        return  ResponseEntity.ok().body(order);

    }
}
