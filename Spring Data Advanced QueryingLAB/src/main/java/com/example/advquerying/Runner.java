package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;

import com.example.advquerying.repositories.ShampooRepository;

import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {


    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired

    public Runner(ShampooRepository shampooRepository, ShampooService shampooService, IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        this.shampooService = shampooService;
    }



    public void run(String... args) throws Exception {
        this.ingredientService.increasePriceOfAllIngredientsByPercent(0.10);
    }
}
