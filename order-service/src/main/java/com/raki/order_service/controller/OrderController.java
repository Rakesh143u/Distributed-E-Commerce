package com.raki.order_service.controller;


import com.raki.order_service.model.Order;
import com.raki.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    public OrderController(OrderService service){
        this.service=service;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id){
        return service.getOrderByid(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        return service.createOrder(order);
    }
}
