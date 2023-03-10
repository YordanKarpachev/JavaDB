package com.example.exercisespringdataintro.entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    @OneToMany(targetEntity = Books.class, mappedBy = "id")
    private Set<Books> books;

}
