package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name = "trailer_id")
    private String trailerId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private float size;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

   private LocalDate releaseDate;

}
