package com.example.exercisejsonprocessing.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

 @ManyToMany(targetEntity = Product.class, mappedBy = "categories")
    private Set<Product> products;

}
