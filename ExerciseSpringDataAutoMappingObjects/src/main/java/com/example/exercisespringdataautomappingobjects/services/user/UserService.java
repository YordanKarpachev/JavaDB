package com.example.exercisespringdataautomappingobjects.services.user;

import com.example.exercisespringdataautomappingobjects.entities.users.RegisterDTO;
import com.example.exercisespringdataautomappingobjects.entities.users.User;

public interface UserService {

    User register(RegisterDTO registerDTO);

    User login();

    void  logout();
}
