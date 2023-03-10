package com.example.exercisespringdataintro.services;

import com.example.exercisespringdataintro.entities.Category;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface CategoryService {

    Set<Category> getRandomCategory();
}
