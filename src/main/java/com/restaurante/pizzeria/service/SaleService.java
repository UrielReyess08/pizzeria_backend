package com.restaurante.pizzeria.service;

import com.restaurante.pizzeria.entity.Sale;
import com.restaurante.pizzeria.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }
    public Optional<Sale> findSaleByNumberSale(String numberSale) {
        return saleRepository.findByNumberSale(numberSale);
    }

    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Sale deleteSale(int id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        sale.setStatus(false); // Cambia el estado a false en lugar de eliminarla físicamente
        return saleRepository.save(sale); // Guarda los cambios en la BD
    }




}
