package com.example.springdataintro.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private int age;

    @OneToMany
    private List<Account> accounts;


}
