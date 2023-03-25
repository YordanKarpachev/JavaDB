package com.example.exercisejsonprocessing.repositories;

import com.example.exercisejsonprocessing.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
