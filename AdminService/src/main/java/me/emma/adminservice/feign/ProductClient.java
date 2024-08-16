package me.emma.adminservice.feign;

import me.emma.adminservice.config.FeignMultipartSupportConfig;
import me.emma.adminservice.pojo.dto.Product;
import me.emma.adminservice.pojo.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "product-service", configuration = FeignMultipartSupportConfig.class)
public interface ProductClient {

    @GetMapping("products")
    List<Product> getAllProducts();

    @GetMapping("products/{id}")
    Product getProductById(@PathVariable Long id);

    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Product createProduct(@RequestPart("productDTO") ProductDTO productDTO,
                          @RequestPart("file") MultipartFile file);

    @PutMapping("products")
    Product updateProduct(@RequestBody Product product);

    @DeleteMapping("products/{id}")
    void deleteProduct(@PathVariable Long id);

    @PostMapping("products/{id}")
    void updateProductStock(@PathVariable Long id, @RequestParam Integer quantity);

}