package me.emma.productservice.service;

import lombok.AllArgsConstructor;
import me.emma.productservice.entity.Product;
import me.emma.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
            Product productInDb = existingProduct.get();
            productInDb.setName(product.getName());
            productInDb.setPrice(product.getPrice());
            productInDb.setDescription(product.getDescription());
            productInDb.setImage(product.getImage());
            productInDb.setStock(product.getStock());

            return productRepository.save(productInDb);
        } else {
            return null;
        }

    }

    public Product updateProductStock(Long id, Integer quantity) {
     Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            Integer newStock = existingProduct.getStock() - quantity;
            existingProduct.setStock(newStock);
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

}
