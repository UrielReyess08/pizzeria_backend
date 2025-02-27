package com.restaurante.pizzeria.controller;

import com.restaurante.pizzeria.entity.Product;
import com.restaurante.pizzeria.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/allProducts")
    public List<Product> findAllActiveProducts() {
        return productService.findAllActiveProducts();
    }

    @GetMapping("/code/{code}")
    public List<Product> findProductByCode(@PathVariable String code) {
        return productService.findProductByCode(code);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.saveProduct(product));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int id) {
        return productService.updateProduct(product, id);
    }

    @PutMapping("/updateStatus/{id}")
    public Product updateProductStatus(@PathVariable int id) {
        return productService.updateProductStatus(id);
    }

}
