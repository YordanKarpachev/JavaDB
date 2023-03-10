package com.example.exercisespringdataintro.repositories;

import com.example.exercisespringdataintro.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}
