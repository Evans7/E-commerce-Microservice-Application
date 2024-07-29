package me.emma.orderservice.repository;

import me.emma.orderservice.pojo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
