package com.example.exercisejsonprocessing.repositories;

import com.example.exercisejsonprocessing.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
