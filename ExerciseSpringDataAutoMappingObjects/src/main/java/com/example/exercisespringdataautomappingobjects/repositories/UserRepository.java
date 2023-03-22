package com.example.exercisespringdataautomappingobjects.repositories;

import com.example.exercisespringdataautomappingobjects.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User user);
}
