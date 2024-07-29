package me.emma.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.orderservice.pojo.entity.CartItem;
import me.emma.orderservice.pojo.entity.Orders;
import me.emma.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/cart")
    public ResponseEntity addProductToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        log.info("Adding product to cart {}", productId);
        orderService.addProductToCart(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity removeProductFromCart(@RequestParam Long productId) {
        log.info("Removing product from cart {}", productId);
        orderService.removeProductFromCart(productId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/cart")
    public ResponseEntity updateProductQuantity(@RequestParam Long productId) {
        log.info("Updating product quantity to cart {}", productId);
        orderService.updateProductQuantity(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartItem>> getCartItems() {
        log.info("Getting cart items");
        List<CartItem> cart = orderService.getCartItems();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/order/checkout")
    public ResponseEntity<Orders> checkout() {
        log.info("Checking out cart");
        Orders order = orderService.checkout();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
