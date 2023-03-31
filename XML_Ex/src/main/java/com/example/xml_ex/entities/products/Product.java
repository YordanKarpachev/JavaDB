package com.example.xml_ex.entities.products;


import com.example.xml_ex.entities.categories.Category;
import com.example.xml_ex.entities.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)

    private String name;

    private BigDecimal price;

    @ManyToOne
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

    public Product(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
        this.categories = new HashSet<>();
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
