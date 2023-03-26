package com.example.exercisejsonprocessing.entities.products;

import java.math.BigDecimal;

public class ProductDTO {
    private String name;
    private BigDecimal price;

    private String seller;

    public ProductDTO(String name, BigDecimal price, String firstName, String lastname) {
        this.name = name;
        this.price = price;
        if(firstName == null){
            this.seller = lastname;
        } else {

            this.seller = firstName + " " + lastname;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
