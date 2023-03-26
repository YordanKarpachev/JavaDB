package com.example.exercisejsonprocessing.entities.user;

import com.example.exercisejsonprocessing.entities.products.Product;
import com.example.exercisejsonprocessing.entities.products.SoldProductDTO;

import java.util.List;
import java.util.Set;

public class UserWithSoldItemsDTO {
    private String firstName;
    private String lastName;

    private List<SoldProductDTO> purchasedItems;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductDTO> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<SoldProductDTO> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }


}
