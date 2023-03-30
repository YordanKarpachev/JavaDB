package com.example.xml_ex;


import com.example.xml_ex.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService seedService;



    @Autowired

    public ConsoleRunner(SeedService seedService){
    this.seedService = seedService;

    }

    @Override
    public void run(String... args) throws Exception {

        this.seedService.seedUsers();

    }

}
