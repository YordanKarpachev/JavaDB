package com.example.exercisespringdataautomappingobjects.services.user;

import com.example.exercisespringdataautomappingobjects.entities.users.RegisterDTO;
import com.example.exercisespringdataautomappingobjects.entities.users.User;
import com.example.exercisespringdataautomappingobjects.repositories.UserRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO registerDTO) {

        ModelMapper mapper = new ModelMapper();

        User user =   mapper.map(registerDTO, User.class);

        if(userRepository.count() == 0){
            user.setAdministrator(true);
        }
        this.userRepository.save(user);

        return user;

    }

    @Override
    public User login() {
        return null;
    }

    @Override
    public void logout() {

    }
}
