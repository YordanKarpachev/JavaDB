package com.example.exercisespringdataautomappingobjects;


import com.example.exercisespringdataautomappingobjects.services.user.RunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class Runner implements CommandLineRunner {


    @Autowired
    private RunnerService runnerService;


    @Override
    public void run(String... args) throws Exception {


        Scanner scanner = new Scanner(System.in);



        while (true){
            String input = scanner.nextLine();
        try {
            this.runnerService.execute(input);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        if (input.equals("Logout")){
            return;
        }
        }

    }


}
