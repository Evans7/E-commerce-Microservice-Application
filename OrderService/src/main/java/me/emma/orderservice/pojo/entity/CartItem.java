package me.emma.orderservice.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.emma.orderservice.pojo.dto.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Product product;
    private Integer quantity;
}
