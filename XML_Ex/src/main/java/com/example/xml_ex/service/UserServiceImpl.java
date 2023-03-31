package com.example.xml_ex.service;


import com.example.xml_ex.entities.user.ExportUserWithSoldItems;
import com.example.xml_ex.entities.user.ExportUsersWithSoldItems;
import com.example.xml_ex.entities.user.User;
import com.example.xml_ex.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public ExportUsersWithSoldItems getAllUsersWithSoldItems() {
        List<User> users = this.userRepository.getAllWIthSoldItemsAndBuyerIsNotNull();

        List<ExportUserWithSoldItems> collect = users.stream().map(a -> this.modelMapper.map(a, ExportUserWithSoldItems.class))
                .collect(Collectors.toList());


        return new ExportUsersWithSoldItems(collect);
    }
}
