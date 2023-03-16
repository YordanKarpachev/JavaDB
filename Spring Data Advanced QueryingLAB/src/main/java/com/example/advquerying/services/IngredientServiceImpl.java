package com.example.advquerying.services;


import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl  implements  IngredientService{

    @Autowired
    private final IngredientRepository ingredientRepository;

    private final ShampooRepository shampooRepository;
    public IngredientServiceImpl(IngredientRepository ingredientRepository, ShampooRepository shampooRepository) {
        this.ingredientRepository = ingredientRepository;
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Ingredient> selectIngredientStartWithGivenLatter(String m) {
        return ingredientRepository.findByNameStartingWith(m);
    }

    @Override
    public List<Ingredient> selectByNamesIn(List<String> lavender) {
        return  this.ingredientRepository.findByNameInOrderByPriceAsc(lavender);
    }

    @Override
    public int countWithPriceLowerThen(BigDecimal price) {
        return this.ingredientRepository.countByPriceLessThan(price);
    }

    @Override
    @Transactional
    public int deleteByName(String name) {
        return this.ingredientRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void increasePriceOfAllIngredientsByPercent(double v) {
        BigDecimal bigDecimal = BigDecimal.valueOf(v);
        this.ingredientRepository.increasePriceByPercent(bigDecimal);
    }

    public  List<Shampoo> selectByIngredientsCount(int count){
        return this.shampooRepository.findByIngredientCountLessThan(count);
    }
}
