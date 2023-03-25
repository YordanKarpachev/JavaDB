package com.example.exercisejsonprocessing.service;

import com.example.exercisejsonprocessing.entities.categories.Category;
import com.example.exercisejsonprocessing.entities.products.Product;
import com.example.exercisejsonprocessing.repositories.CategoryRepository;
import com.example.exercisejsonprocessing.repositories.ProductRepository;
import com.example.exercisejsonprocessing.repositories.UserRepository;
import com.example.exercisejsonprocessing.entities.user.User;
import com.example.exercisejsonprocessing.entities.user.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private final String USER_PATH = "src/main/resources/json_data/users.json";

    private final String PRODUCTS_PATH = "src/main/resources/json_data/products.json";

    private final String CATEGORIES_PATH = "src/main/resources/json_data/categories.json";

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private FileReader fileReader;

    private Gson gson;

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;


    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().setPrettyPrinting().create();


    }

    @Override
    public void seedUsers() throws FileNotFoundException {


        this.fileReader = new FileReader(USER_PATH);

        UserDTO[] userDTOS = this.gson.fromJson(fileReader, UserDTO[].class);


        List<User> collect = Arrays.stream(userDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, User.class))
                .collect(Collectors.toList());
          this.userRepository.saveAll(collect);

    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        this.fileReader = new FileReader(PRODUCTS_PATH);
        Product[] products = gson.fromJson(fileReader, Product[].class);


        List<Product> collect = Arrays.stream(products)
                .map(a -> modelMapper.map(a, Product.class))
                .map(this::setSeller)
                .map(this::setBeyer)
                .map(this::setCategories)
                .collect(Collectors.toList());


        this.productRepository.saveAll(collect);

    }

    private Product setCategories(Product product) {
        long countCategories = this.categoryRepository.count();
        int i = new Random().nextInt((int) countCategories);

        for (int j = 0; j < i; j++) {
            Optional<Category> byId = this.categoryRepository.findById(new Random().nextInt((int) countCategories) + 1);
            product.setCategories(byId.get());

        }

        return product;
    }

    private Product setBeyer(Product product) {

        int i = new Random().nextInt(10);
        if(i < 3){
            long count = this.userRepository.count();
            int i1 = new Random().nextInt((int) count) + 1;
            Optional<User> byId = this.userRepository.findById(i1);
            product.setBuyer(byId.get());
        }

        return product;
    }

    private Product setSeller(Product product) {
        long count = this.userRepository.count();
        int i = new Random().nextInt((int) count) + 1;

        Optional<User> byId = this.userRepository.findById(i);
        product.setSeller(byId.get());

        return product;
    }

    @Override
    public void seedCategories() throws FileNotFoundException {

        this.fileReader = new FileReader(CATEGORIES_PATH);

        Category[] categories = gson.fromJson(fileReader, Category[].class);
        List<Category> collect = Arrays.stream(categories).map(a -> modelMapper.map(a, Category.class)).collect(Collectors.toList());


        this.categoryRepository.saveAll(collect);
    }
}
