package me.emma.adminservice.service;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AdminService {

    private final ProductClient productClient;
    private final StreamBridge streamBridge;


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

        // Get the part of the URL after the last '/'
        String imageName = image.substring(image.lastIndexOf('/') + 1);
        log.info(imageName);

        streamBridge.send("sendDeletedProduct-out-0", imageName);
        productClient.deleteProduct(id);
    }

}
