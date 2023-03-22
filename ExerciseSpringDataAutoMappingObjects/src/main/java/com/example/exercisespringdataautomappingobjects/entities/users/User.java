package com.example.exercisespringdataautomappingobjects.entities.users;

import com.example.exercisespringdataautomappingobjects.entities.Game;
import com.example.exercisespringdataautomappingobjects.entities.Order;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private boolean isAdministrator;

    @ManyToMany
    private Set<Game> games;

    @OneToMany(targetEntity = Order.class, mappedBy = "buyer")
    private Set<Order> orders;


    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }


    public User() {
    }

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }


    public boolean isAdministrator() {
        return isAdministrator;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
