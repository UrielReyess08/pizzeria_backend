package com.restaurante.pizzeria.controller;

import com.restaurante.pizzeria.entity.Sale;
import com.restaurante.pizzeria.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Sale> findActiveSales() {
        return saleService.findAllSales()
                .stream()
                .filter(Sale::isStatus)
                .collect(Collectors.toList());
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

    @PutMapping("/disable/{numberSale}")
    public ResponseEntity<Map<String, String>> disableSale(@PathVariable String numberSale) {
        Optional<Sale> optionalSale = saleService.findSaleByNumberSale(numberSale);

        if (optionalSale.isPresent()) {
            Sale sale = optionalSale.get();
            sale.setStatus(false);  // Desactivar la venta
            saleService.saveSale(sale);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Venta desactivada correctamente");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Venta no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
