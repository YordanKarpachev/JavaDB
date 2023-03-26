package com.example.exercisejsonprocessing.service;

import com.example.exercisejsonprocessing.entities.user.User;
import com.example.exercisejsonprocessing.entities.user.UserWithSoldItemsDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldItemsDTO> getAllUsersWithSoldItemsWithBuyer();
}
