package me.emma.productservice.consumers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.productservice.entity.OrderPlacedEvent;
import me.emma.productservice.feign.ImageClient;
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
    private final ImageClient imageClient;

    @Bean
    public Consumer<String> productResponse() {
        return (String image) -> {
            imageClient.deleteImage(image);
            System.out.println("Received image to delete image from image service: " + image);
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