package com.example.exercisejsonprocessing.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private BigDecimal price;

    @ManyToOne(targetEntity = User.class)
    private int buyerId;

    @ManyToOne(targetEntity = User.class)
    private int sellerId;

  @ManyToMany
    private Set<Category> categories;
}
