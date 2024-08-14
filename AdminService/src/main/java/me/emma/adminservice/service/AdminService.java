package me.emma.adminservice.service;

import me.emma.adminservice.feign.ProductClient;
import me.emma.adminservice.pojo.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final ProductClient productClient;

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
         productClient.deleteProduct(id);
    }

}
