package me.emma.productservice.restController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.productservice.entity.Product;
import me.emma.productservice.entity.ProductDTO;
import me.emma.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Slf4j
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(@RequestBody Product product) {

        return productService.createProduct(product);
    }

//    @PostMapping("/imageUpload")
//    public String uploadProductImage(MultipartFile file) {
//        String imageUrl = productService.getProductImageUrl(file);
//        log.info("Image url: {}", imageUrl);
//        return imageUrl;
//    }

//
//    @PostMapping("/upload")
//    public String uploadFile(@RequestPart("file") MultipartFile file) {
//        return productService.getProductImageUrl(file);
//    }

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
