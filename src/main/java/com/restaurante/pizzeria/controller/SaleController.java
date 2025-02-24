package com.restaurante.pizzeria.controller;

import com.restaurante.pizzeria.entity.Sale;
import com.restaurante.pizzeria.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/{id}")
    public Optional<Sale> findSaleById(@PathVariable int id) {
        return saleService.findSaleById(id);
    }

    @GetMapping("/all")
    public List<Sale> findAllSales() {
        return saleService.findAllSales();
    }
    @PostMapping("/save")
    public Sale saveSale(@RequestBody Sale sale) {
        return saleService.saveSale(sale);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable int id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

}
