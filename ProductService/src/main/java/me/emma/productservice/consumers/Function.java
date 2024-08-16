package me.emma.productservice.consumers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.productservice.entity.OrderPlacedEvent;
import me.emma.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.FunctionConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
@AllArgsConstructor
public class Function {

    private final ProductService productService;

    @Bean
    public Consumer<Long> productResponse() {
        return (Long id) -> {
            System.out.println("Received product ID to delete image from image service: " + id);
        };
    }


    @Bean
    public Consumer<OrderPlacedEvent> orderPlacedEventConsumer() {
        log.info("OrderPlacedEventConsumer bean registered");
        return event -> {
            event.getCartItems().forEach(item -> {
                log.info("Received cart item: " + item);
                productService.updateProductStock(item.getProductId(), item.getQuantity());
            });
        };
    }


}