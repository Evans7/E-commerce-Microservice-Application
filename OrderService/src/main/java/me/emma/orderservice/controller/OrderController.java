package me.emma.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.orderservice.pojo.dto.CartItem;
import me.emma.orderservice.pojo.entity.Orders;
import me.emma.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<Orders> createOrder(@RequestBody List<CartItem> items) {
        log.info("Checking out cart");
        Orders order = orderService.createOrder(items);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        log.info("Checking out orders");
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }



}
