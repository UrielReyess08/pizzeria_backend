package com.restaurante.pizzeria.service;

import com.restaurante.pizzeria.entity.Product;
import com.restaurante.pizzeria.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllActiveProducts() {
        return productRepository.findAllActiveProducts();
    }

    public List<Product> findProductByCode(String code) {
        return productRepository.findProductByCode(code);
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, int id) {
        Product eProduct = productRepository.findById(id).orElseThrow(null);
            if(eProduct != null){
                eProduct.setStatus(product.isStatus());
                eProduct.setCode(product.getCode());
                eProduct.setName(product.getName());
                eProduct.setPrice(product.getPrice());
                eProduct.setStock(product.getStock());
                eProduct.setSize(product.getSize());

            }
            return productRepository.save(product);
    }
    public Product updateProductStatus(int id) {
        Product eProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        eProduct.setStatus(false);
        return productRepository.save(eProduct);
    }




}
