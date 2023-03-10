package com.example.exercisespringdataintro.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1000)
    private String description;

    private int copies;

    @Column(name = "edition_type")
    private EditionTypes editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "age_restriction", nullable = false)
    private int ageRestriction;

    @ManyToOne
    private Author author;

    public Book(String description, int copies, EditionTypes editionType, BigDecimal price, LocalDate releaseDate, int ageRestriction, Author author) {
        this.description = description;
        this.copies = copies;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
    }


    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(int copies, EditionTypes editionType, BigDecimal price, LocalDate releaseDate, int ageRestriction, Author author) {
        this.copies = copies;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
    }
}
