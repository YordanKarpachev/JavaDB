package com.example.exercisejsonprocessing;

import com.example.exercisejsonprocessing.entities.products.Product;
import com.example.exercisejsonprocessing.entities.products.ProductDTO;
import com.example.exercisejsonprocessing.service.ProductService;
import com.example.exercisejsonprocessing.service.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService seedService;
    private final ProductService productService;

    @Autowired

    public ConsoleRunner(SeedService seedService, ProductService productService) {
        this.seedService = seedService;
        this.productService = productService;

    }

    @Override
    public void run(String... args) throws Exception {

        List<ProductDTO> allProductsInRangeWhichHaveNoBuyer = this.productService.getAllProductsInRangeWhichHaveNoBuyer(500, 1000);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(allProductsInRangeWhichHaveNoBuyer);
        System.out.println(s);


    }
}
