package com.restaurante.pizzeria.controller;

import com.restaurante.pizzeria.entity.Sale;
import com.restaurante.pizzeria.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/{numberSale}")
    public Optional<Sale> findSaleByNumberSale(@PathVariable String numberSale) {
        return saleService.findSaleByNumberSale(numberSale);
    }

    @GetMapping("/allSales")
    public List<Sale> findAllSales() {
        return saleService.findAllSales();
    }
    @PostMapping("/save")
    public Sale saveSale(@RequestBody Sale sale) {
        return saleService.saveSale(sale);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteSale(@PathVariable int id) {
        saleService.deleteSale(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Venta eliminada correctamente");
        return ResponseEntity.ok(response);
    }


}
