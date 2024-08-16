package me.emma.orderservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.orderservice.feign.ProductClient;
import me.emma.orderservice.pojo.dto.CartItem;
import me.emma.orderservice.pojo.dto.OrderPlacedEvent;
import me.emma.orderservice.pojo.dto.Product;
import me.emma.orderservice.pojo.entity.OrderItem;
import me.emma.orderservice.pojo.entity.Orders;
import me.emma.orderservice.repository.OrderItemRepository;
import me.emma.orderservice.repository.OrdersRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final ProductClient productClient;
    private final OrderItemRepository orderItemRepository;
    private final OrdersRepository ordersRepository;
    private final StreamBridge streamBridge;

    public Orders createOrder(List<CartItem> items) {
        Orders orders = new Orders();
        orders.setOrderDate(LocalDateTime.now());
        BigDecimal totalPrice = BigDecimal.ZERO;
        orders = ordersRepository.save(orders);
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : items) {
            log.info(String.valueOf(item));
            OrderItem orderItem = new OrderItem();
            Product product = productClient.getProductById(item.getProductId());

            orderItem.setOrders(orders);
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
            log.info("product price: {}", product.getPrice());
            totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        log.info("total price: {}", totalPrice);
        orders.setTotalPrice(totalPrice);
        orders.setOrderItems(orderItems);
        OrderPlacedEvent event = new OrderPlacedEvent(orders.getOrderId(), items);
        streamBridge.send("orderPlaced-out-0", event);
        log.info("OrderPlacedEvent sent to Kafka: {}", event);
        return ordersRepository.save(orders);

    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
