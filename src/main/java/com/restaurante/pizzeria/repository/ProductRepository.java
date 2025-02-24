package com.restaurante.pizzeria.repository;

import com.restaurante.pizzeria.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {



    @Query("SELECT P FROM Product P WHERE P.code = :code")
    List<Product> findProductByCode(@Param("code") String code);

}
