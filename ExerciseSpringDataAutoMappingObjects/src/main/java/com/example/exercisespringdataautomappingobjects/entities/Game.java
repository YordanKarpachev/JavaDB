package com.example.exercisespringdataautomappingobjects.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
@Column(nullable = false)
    private String title;

    @Column(name = "trailer_id", nullable = false)
    private String trailerId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private float size;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;
@Column(name = "release_date")
   private LocalDate releaseDate;

}
