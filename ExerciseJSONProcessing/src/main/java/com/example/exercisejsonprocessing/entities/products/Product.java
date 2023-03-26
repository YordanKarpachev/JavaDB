package com.example.exercisejsonprocessing.entities.products;

import com.example.exercisejsonprocessing.entities.categories.Category;
import com.example.exercisejsonprocessing.entities.user.User;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private BigDecimal price;

    @ManyToOne(targetEntity = User.class)
    private User buyer;



  @ManyToMany
    private Set<Category> categories;

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @ManyToOne(targetEntity = User.class)
   private User seller;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }




    public Set<Category> getCategories() {
        return categories;
    }

    public Product() {
        this.categories = new HashSet<>();
    }





    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




    public void setCategories(Category categoriy) {
        this.categories.add(categoriy);
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
