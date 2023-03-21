package entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User buyer;

    @ManyToMany
    private Set<Game> products;

}
