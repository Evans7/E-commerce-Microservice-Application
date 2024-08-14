package me.emma.productservice.restController;

import lombok.AllArgsConstructor;
import me.emma.productservice.entity.Product;
import me.emma.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PutMapping("/product/updateStock")
    public Product updateProductStock(@RequestParam Long id, @RequestParam Integer quantity) {
        return productService.updateProductStock(id, quantity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
