package com.example.exercisespringdataautomappingobjects;


import com.example.exercisespringdataautomappingobjects.entities.users.RegisterDTO;
import com.example.exercisespringdataautomappingobjects.entities.users.User;
import com.example.exercisespringdataautomappingobjects.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class Runner implements CommandLineRunner {

    private final String registerUserCommand = "RegisterUser";
    private final String loginUserCommand = "LoginUser";

    private final String logoutUserCommand = "Logout";

    private final UserService UserServiceImpl;

    @Autowired
    public Runner(UserService userServiceImpl) {
        UserServiceImpl = userServiceImpl;
    }


    @Override
    public void run(String... args) throws Exception {


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        this.execute(input);
    }

    private String execute(String input) {

        String[] commandParts = input.split("\\|");

        String command = commandParts[0];

        String commandOutput = switch (command) {
            case registerUserCommand -> {

                RegisterDTO registerDTO = new RegisterDTO(commandParts);
                User user = this.UserServiceImpl.register(registerDTO);


                yield  String.format("%s was registred", user.getFullName());
            }

            default ->  "unknown command";
        };
        System.out.println(commandOutput);

        return commandOutput;



    }
}
