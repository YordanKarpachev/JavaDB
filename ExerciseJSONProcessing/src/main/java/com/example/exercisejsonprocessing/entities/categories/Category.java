package com.example.exercisejsonprocessing.entities.categories;

import com.example.exercisejsonprocessing.entities.products.Product;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

 @ManyToMany(targetEntity = Product.class, mappedBy = "categories")
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
