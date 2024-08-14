package me.emma.orderservice.service;

import me.emma.orderservice.constant.OrderConstant;
import me.emma.orderservice.feign.ProductClient;
import me.emma.orderservice.pojo.dto.CartItem;
import me.emma.orderservice.pojo.entity.OrderItem;
import me.emma.orderservice.pojo.entity.Orders;
import me.emma.orderservice.pojo.dto.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private Map<Long, CartItem> cart = new HashMap<>();
    private final ProductClient productClient;

    public OrderService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public void addProductToCart(Long productId) {
        Product product = productClient.getProductById(productId);

        // check if the product is in the cart, if it is, add one more quantity
        if (cart.containsKey(productId)) {
            CartItem cartItem = cart.get(productId);
            cartItem.setQuantity(cartItem.getQuantity() + OrderConstant.QUANTITY);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(OrderConstant.QUANTITY);
            cart.put(productId, cartItem);
        }
    }

    public void removeProductFromCart(Long productId) {
        if (cart.containsKey(productId)) {
            cart.remove(productId);
        }
    }

    public void updateProductQuantity(Long productId) {
        if (cart.containsKey(productId)) {
            CartItem cartItem = cart.get(productId);
            // click + button to add one more quantity
            cartItem.setQuantity(cartItem.getQuantity() + OrderConstant.QUANTITY);
        }
    }


    public List<CartItem> getCartItems() {
        return new ArrayList<>(cart.values());
    }

    @Transactional
    public Orders checkout() {
        Orders orders = new Orders();
        orders.setOrderDate(LocalDateTime.now());
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setProductId(cartItem.getProduct().getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
            // calculate total price
            totalPrice = totalPrice.add(cartItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getQuantity())));

        }
        orders.setTotalPrice(totalPrice);
        orders.setOrderItems(orderItems);
        cart.clear();
        return orders;

    }
}
