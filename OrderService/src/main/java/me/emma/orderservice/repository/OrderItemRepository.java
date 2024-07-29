package me.emma.orderservice.repository;

import me.emma.orderservice.pojo.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
