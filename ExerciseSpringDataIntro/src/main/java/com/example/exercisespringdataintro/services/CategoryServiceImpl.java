package com.example.exercisespringdataintro.services;

import com.example.exercisespringdataintro.entities.Category;
import com.example.exercisespringdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategory() {
        long size = this.categoryRepository.count();

        Random random = new Random();

        int categoriesCount = random.nextInt((int) size) + 1;

        Set<Integer> categoriesIds = new HashSet<>();

        for (int i = 0; i < categoriesCount; i++) {

            int nextId = random.nextInt((int) size) + 1;
            categoriesIds.add(nextId);

        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesIds);

        return new HashSet<>(allById);

    }
}
