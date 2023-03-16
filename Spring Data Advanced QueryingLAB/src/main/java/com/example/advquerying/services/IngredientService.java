package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<Ingredient> selectIngredientStartWithGivenLatter(String m
    );

    List<Ingredient> selectByNamesIn(List<String> lavender);

    int countWithPriceLowerThen(BigDecimal price);

    int deleteByName(String name);

    void increasePriceOfAllIngredientsByPercent(double v);

}
