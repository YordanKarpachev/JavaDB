package com.example.exercisespringdataautomappingobjects.services.user;

import com.example.exercisespringdataautomappingobjects.entities.users.RegisterDTO;
import com.example.exercisespringdataautomappingobjects.entities.users.User;

import java.util.Optional;

public interface UserService {

    User register(RegisterDTO registerDTO);

  String login(String... args);

    String  logout();
}
