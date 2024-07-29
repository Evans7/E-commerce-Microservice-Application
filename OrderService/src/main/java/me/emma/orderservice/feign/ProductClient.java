package me.emma.orderservice.feign;

import me.emma.orderservice.pojo.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("products/{id}")
    Product getProductById(@PathVariable Long id);


    @PostMapping("products/{id}")
    void updateProductStock(@PathVariable Long id, @RequestParam Integer quantity);

}
