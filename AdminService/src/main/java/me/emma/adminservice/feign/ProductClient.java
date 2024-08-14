package me.emma.adminservice.feign;

import me.emma.adminservice.pojo.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("products")
    List<Product> getAllProducts();

    @GetMapping("products/{id}")
    Product getProductById(@PathVariable Long id);

    @PostMapping("products")
    Product createProduct(@RequestBody Product product);

    @PutMapping("products")
    Product updateProduct(@RequestBody Product product);

    @DeleteMapping("products/{id}")
    void deleteProduct(@PathVariable Long id);

    @PostMapping("products/{id}")
    void updateProductStock(@PathVariable Long id, @RequestParam Integer quantity);

}