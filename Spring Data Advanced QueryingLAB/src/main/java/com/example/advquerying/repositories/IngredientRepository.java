package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String m);

    List<Ingredient> findByNameInOrderByPriceDesc(List<String> lavender);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> lavender);

    int countByPriceLessThan(BigDecimal price);

    int deleteByName(String name);

    @Modifying
    @Query("update Ingredient i set i.price = i.price + i.price * :multiply")
    void increasePriceByPercent(@Param("multiply") BigDecimal percent);
}
