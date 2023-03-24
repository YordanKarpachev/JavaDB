package com.example.exercisejsonprocessing.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    private int age;

    @OneToMany(targetEntity = Product.class, mappedBy = "sellerId")
    private Set<Product> sellingItems;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyerId")
    private Set<Product> purchasedItems;
}
