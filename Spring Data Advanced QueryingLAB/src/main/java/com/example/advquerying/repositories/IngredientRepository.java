package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String m);

    List<Ingredient> findByNameInOrderByPriceDesc(List<String> lavender);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> lavender);

}
