package me.emma.adminservice.service;

import lombok.extern.slf4j.Slf4j;
import me.emma.adminservice.feign.ProductClient;
import me.emma.adminservice.pojo.dto.Product;
import me.emma.adminservice.pojo.dto.ProductDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminService {

    private final ProductClient productClient;
    StreamBridge streamBridge;

    public AdminService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public List<Product> getProducts() {
        return productClient.getAllProducts();
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productClient.getProductById(id));
    }

    public Product createProduct(Product product){
        return productClient.createProduct(product);
    }

    public Product updateProduct(Product product){
        return productClient.updateProduct(product);
    }

    public void deleteProduct(Long id){
        log.info("Sending data to product service");
        String image = productClient.getProductById(id).getImage();
        streamBridge.send("sendDeletedProduct-out-0", image);
         productClient.deleteProduct(id);
    }

}
