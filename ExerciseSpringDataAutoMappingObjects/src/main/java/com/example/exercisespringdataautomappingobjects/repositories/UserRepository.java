package com.example.exercisespringdataautomappingobjects.repositories;

import com.example.exercisespringdataautomappingobjects.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User user);

   Optional< User> findByEmailAndPassword(String email, String password);
}
