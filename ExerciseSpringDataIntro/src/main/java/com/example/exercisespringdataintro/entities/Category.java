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
    private Set<Book> books;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
