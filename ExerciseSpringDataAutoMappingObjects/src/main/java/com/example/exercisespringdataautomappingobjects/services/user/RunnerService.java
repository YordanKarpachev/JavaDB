package com.example.exercisespringdataautomappingobjects.services.user;

import com.example.exercisespringdataautomappingobjects.entities.users.RegisterDTO;
import com.example.exercisespringdataautomappingobjects.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RunnerService {
    private final String registerUserCommand = "RegisterUser";
    private final String loginUserCommand = "LoginUser";

    private final String logoutUserCommand = "Logout";


    private final UserService UserServiceImpl;

    @Autowired
    public RunnerService(UserService userServiceImpl) {
        UserServiceImpl = userServiceImpl;
    }

    public String execute(String input) {

        String[] commandParts = input.split("\\|");

        String command = commandParts[0];

        String commandOutput = switch (command) {
            case registerUserCommand -> {
                RegisterDTO registerDTO = new RegisterDTO(commandParts);
                User user = this.UserServiceImpl.register(registerDTO);
                yield String.format("%s was registred", user.getFullName());
            }
            case loginUserCommand -> this.loginUser(commandParts);
            case logoutUserCommand -> this.UserServiceImpl.logout();

            default -> "unknown command";
        };

        System.out.println(commandOutput);

        return commandOutput;


    }

    private String loginUser(String[] commandParts) {
        return this.UserServiceImpl.login(commandParts);


    }


}
