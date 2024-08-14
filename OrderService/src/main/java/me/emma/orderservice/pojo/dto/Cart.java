package me.emma.orderservice.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private List<CartItem> cartItems;
}
