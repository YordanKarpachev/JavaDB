package com.example.advquerying;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {


    private final ShampooRepository shampooRepository;

    @Autowired
    public Runner(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Shampoo> allByBrand = this.shampooRepository.findAllByBrand("Strength & Nourishing Elixir");

        System.out.println();
    }
}
