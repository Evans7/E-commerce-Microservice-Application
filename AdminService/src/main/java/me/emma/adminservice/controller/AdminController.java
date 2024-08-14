package me.emma.adminservice.controller;


import lombok.AllArgsConstructor;
import me.emma.adminservice.pojo.dto.Product;
import me.emma.adminservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/product")
    public List<Product> getAdminAllProducts() {
        return adminService.getProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getAdminProductById(@PathVariable Long id) {
        Optional<Product> product = adminService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/product")
    public Product createAdminProduct(@RequestBody Product product) {
        return adminService.createProduct(product);
    }

    @PutMapping("/product")
    public Product updateAdminProduct(@RequestBody Product product) {
        return adminService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
