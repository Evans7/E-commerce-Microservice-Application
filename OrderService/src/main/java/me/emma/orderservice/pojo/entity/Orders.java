package me.emma.orderservice.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Orders {

    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private List<OrderItem> orderItems;

}
