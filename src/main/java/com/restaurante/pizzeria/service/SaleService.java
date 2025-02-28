package com.restaurante.pizzeria.service;

import com.restaurante.pizzeria.entity.Product;
import com.restaurante.pizzeria.entity.Sale;
import com.restaurante.pizzeria.repository.ProductRepository;
import com.restaurante.pizzeria.repository.SaleRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Sale saveSale(Sale sale) {

        Product product = productRepository.findById(sale.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (!product.isStatus()) {
            throw new RuntimeException("El producto " + product.getName() + " no está disponible para la venta.");
        }
        if (product.getStock() < sale.getQuantity()) {
            throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
        }
        product.setStock(product.getStock() - sale.getQuantity());
        if (product.getStock() == 0) {
            product.setStatus(false);
        }
        productRepository.save(product);
        return saleRepository.save(sale);
    }

    public Optional<Sale> findSaleByNumberSale(String numberSale) {
        return saleRepository.findByNumberSale(numberSale);
    }

    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }


}
