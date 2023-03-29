package com.example.xml_ex.repositories;


import com.example.xml_ex.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;




@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
