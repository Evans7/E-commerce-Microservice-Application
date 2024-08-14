package me.emma.productservice.consumers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class Function {

    @Bean
    public Consumer<Long> productResponse() {
        return (Long id) -> {
            System.out.println("Received product ID to delete image from image service: " + id);
        };
    }

}