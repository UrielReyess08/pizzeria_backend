package com.restaurante.pizzeria.repository;

import com.restaurante.pizzeria.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Optional<Sale> findByNumberSale(String numberSale);
}