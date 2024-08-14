package me.emma.adminservice.feign;

import me.emma.adminservice.pojo.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("order-service")
public interface OrderClient {


        @GetMapping("products/{id}")
        Product getOrderById(@PathVariable Long id);


        @PostMapping("products/{id}")
        void updateProductStock(@PathVariable Long id, @RequestParam Integer quantity);

}
