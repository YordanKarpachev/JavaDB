package com.example.exercisejsonprocessing;

import com.example.exercisejsonprocessing.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private  SeedService seedService;

    @Autowired

    public ConsoleRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedService.seedAll();

    }
}
