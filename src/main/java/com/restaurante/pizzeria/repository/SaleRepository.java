package com.restaurante.pizzeria.repository;

import com.restaurante.pizzeria.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}