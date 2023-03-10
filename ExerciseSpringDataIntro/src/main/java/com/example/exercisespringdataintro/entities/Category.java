package com.example.exercisespringdataintro.entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;


    @OneToMany
    private Set<Books> books;



}
