package com.example.exercisespringdataautomappingobjects.entities;

import com.example.exercisespringdataautomappingobjects.entities.users.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> products;

}
