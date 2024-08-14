package me.emma.adminservice.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private List<OrderItem> orderItems;
}
