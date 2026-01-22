package com.mycommerce.controller;

import com.mycommerce.enums.StatusOrder;
import com.mycommerce.model.Order;
import com.mycommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> newCreateOrder(@RequestBody Order order){
        Order newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> searchByOrderCustomer (@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.consultByCustomerOrder(customerId));
    }

    @PatchMapping("/{id}/statusOrder")
    public ResponseEntity<Order> updateStatusOrder(@PathVariable Long id, @RequestParam StatusOrder statusOrder) {
        return ResponseEntity.ok(orderService.updateStatusOrder(id, statusOrder));
    }


}
