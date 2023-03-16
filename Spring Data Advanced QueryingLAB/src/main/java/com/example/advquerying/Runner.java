package com.example.advquerying;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {


    private final ShampooRepository shampooRepository;

    private final ShampooService shampooService;

    @Autowired
    public Runner(ShampooRepository shampooRepository, ShampooService shampooService) {
        this.shampooRepository = shampooRepository;
        this.shampooService = shampooService;
    }


    @Override
    public void run(String... args) throws Exception {

        List<Shampoo> shampoos = this.shampooService.selectBySizeGreaterThan(BigDecimal.valueOf(5));
        shampoos.forEach(a -> System.out.println(a));
    }
}
