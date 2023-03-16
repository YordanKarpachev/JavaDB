package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findAllByBrand(String brand);

    List<Shampoo> findBySize(Size size);


    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal valueOf);


    @Query("SELECT s from  Shampoo  s JOIN  s.ingredients as i where  i.name in :ingredientNames")
    List<Shampoo> findByIngredientsName(@Param("ingredientName") Set<String> ingredients);

    @Query("SELECT  s from Shampoo  s where s.ingredients.size > :count")
    List<Shampoo> findByIngredientCountBiggerThan(int count);
}
