package entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    @Column(name = "full_name")
    private String fullName;

    private boolean isAdministrator;

    @OneToMany
    private Set<Game> games;


}
