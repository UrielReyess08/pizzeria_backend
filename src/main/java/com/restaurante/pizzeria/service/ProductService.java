package com.restaurante.pizzeria.service;

import com.restaurante.pizzeria.entity.Product;
import com.restaurante.pizzeria.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllActiveProducts() {
        return productRepository.findAll();
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

                eProduct.setCode(product.getCode());
                eProduct.setName(product.getName());
                eProduct.setPrice(product.getPrice());
                eProduct.setStock(product.getStock());

            }
            return productRepository.save(product);
    }
    public Product deleteProduct(int id) {
        Product eProduct = productRepository.findById(id).orElseThrow(null);
        return productRepository.save(eProduct);
    }


}
