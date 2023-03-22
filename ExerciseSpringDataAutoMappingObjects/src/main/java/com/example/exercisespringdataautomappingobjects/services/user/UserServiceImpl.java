package com.example.exercisespringdataautomappingobjects.services.user;

import com.example.exercisespringdataautomappingobjects.entities.users.RegisterDTO;
import com.example.exercisespringdataautomappingobjects.entities.users.User;
import com.example.exercisespringdataautomappingobjects.repositories.UserRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private User currentUser;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        currentUser = null;
    }

    @Override
    public User register(RegisterDTO registerDTO) {

        ModelMapper mapper = new ModelMapper();

        User user = mapper.map(registerDTO, User.class);

        if (userRepository.count() == 0) {
            user.setAdministrator(true);
        }
        this.userRepository.save(user);

        return user;

    }

    @Override
    public String login(String... args) {
        String email = args[1];
        String password = args[2];
        Optional<User> byEmailAndName = this.userRepository.findByEmailAndPassword(email, password);
        if (byEmailAndName.isPresent() && currentUser == null) {
            User user = byEmailAndName.get();
            currentUser = user;
            return String.format("Successfully logged in %s", user.getFullName());
        }


        return "Cannot log in.";
    }

    @Override
    public String logout() {
        if (currentUser == null) {
            return "Cannot log out. No user was logged in.";
        }
        else {
            return String.format("User %s successfully logged out", currentUser.getFullName());
        }
    }
}
