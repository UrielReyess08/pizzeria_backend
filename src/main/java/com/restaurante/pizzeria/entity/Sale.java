package com.restaurante.pizzeria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "sale")
public class Sale {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dni", length = 11, nullable = false)
    private String dni;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "client", length = 100, nullable = false)
    private String client;

    @ManyToOne
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "discount", nullable = false)
    private Boolean discount;


}
