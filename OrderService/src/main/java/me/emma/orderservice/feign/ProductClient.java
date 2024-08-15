package me.emma.orderservice.feign;

import me.emma.orderservice.pojo.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("products/{id}")
    Product getProductById(@PathVariable Long id);


    @PutMapping("products/{id}")
    void updateProductStock(@PathVariable Long id, @RequestParam Integer quantity);

}
