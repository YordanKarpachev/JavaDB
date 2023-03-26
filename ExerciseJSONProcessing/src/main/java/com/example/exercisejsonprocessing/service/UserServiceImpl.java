package com.example.exercisejsonprocessing.service;

import com.example.exercisejsonprocessing.entities.user.User;
import com.example.exercisejsonprocessing.entities.user.UserWithSoldItemsDTO;
import com.example.exercisejsonprocessing.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<UserWithSoldItemsDTO> getAllUsersWithSoldItemsWithBuyer() {
      List<User> listUsers =   this.userRepository.getAllUserWithSoldItems();
        ModelMapper modelMapper = new ModelMapper();
        return listUsers.stream().map(a -> modelMapper.map(a, UserWithSoldItemsDTO.class)).collect(Collectors.toList());

    }
}
