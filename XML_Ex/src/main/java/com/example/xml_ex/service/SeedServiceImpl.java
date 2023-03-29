package com.example.xml_ex.service;


import com.example.xml_ex.entities.categories.Category;
import com.example.xml_ex.entities.products.Product;
import com.example.xml_ex.entities.user.User;
import com.example.xml_ex.repositories.CategoryRepository;
import com.example.xml_ex.repositories.ProductRepository;
import com.example.xml_ex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private final String USER_PATH = "src/main/resources/json_data/users.json";

    private final String PRODUCTS_PATH = "src/main/resources/json_data/products.json";

    private final String CATEGORIES_PATH = "src/main/resources/json_data/categories.json";

    private UserRepository userRepository;



    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;


    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;




    }

    @Override
    public void seedUsers() throws FileNotFoundException {



    }

    @Override
    public void seedProducts() throws FileNotFoundException {


    }

    @Override
    public void seedCategories() throws FileNotFoundException {

    }

    @Override
    public void seedAll() throws FileNotFoundException {
        SeedService.super.seedAll();
    }

    private Product setRandomCategories(Product product) {
        long countCategories = this.categoryRepository.count();
        int i = new Random().nextInt((int) countCategories);

        for (int j = 0; j < i; j++) {
            Optional<Category> byId = this.categoryRepository.findById(new Random().nextInt((int) countCategories) + 1);
            product.setCategories(byId.get());

        }

        return product;
    }

    private Product setRandomBuyer(Product product) {

        int i = new Random().nextInt(10);
        if(i < 3){
            long count = this.userRepository.count();
            int i1 = new Random().nextInt((int) count) + 1;
            Optional<User> byId = this.userRepository.findById(i1);
            product.setBuyer(byId.get());
        }

        return product;
    }

    private Product setRandomSeller(Product product) {
        long count = this.userRepository.count();
        int i = new Random().nextInt((int) count) + 1;

        Optional<User> byId = this.userRepository.findById(i);
        product.setSeller(byId.get());

        return product;
    }

    private Optional<User> getRandomUser(){
        long userCount = this.userRepository.count();
        int randomUserId = new Random().nextInt((int) userCount) + 1;
        return this.userRepository.findById(randomUserId);
    }


}
