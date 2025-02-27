package com.restaurante.pizzeria.service;

import com.restaurante.pizzeria.entity.Product;
import com.restaurante.pizzeria.entity.Sale;
import com.restaurante.pizzeria.repository.ProductRepository;
import com.restaurante.pizzeria.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }
    public Optional<Sale> findSaleByNumberSale(String numberSale) {
        return saleRepository.findByNumberSale(numberSale);
    }

    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    public Sale saveSale(Sale sale) {
        System.out.println("Venta recibida: " + sale);

        if (sale.getProduct() == null || sale.getProduct().getId() == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }

        Product product = productRepository.findById(sale.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));


        if (product.getStock() < sale.getQuantity()) {
            throw new IllegalArgumentException("Stock insuficiente para la venta.");
        }

        product.setStock(product.getStock() - sale.getQuantity());

        if (product.getStock() == 0) {
            product.setStatus(false);
        }

        productRepository.save(product);
        return saleRepository.save(sale);
    }




}
