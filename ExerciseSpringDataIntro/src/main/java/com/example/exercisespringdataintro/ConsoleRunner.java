package com.example.exercisespringdataintro;

import com.example.exercisespringdataintro.services.SeedService;
import com.example.exercisespringdataintro.services.SeedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private SeedServiceImpl seedServiceImpl;

    public ConsoleRunner(SeedServiceImpl seedServiceImpl) {
        this.seedServiceImpl = seedServiceImpl;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedServiceImpl.seedAll();

    }
}
