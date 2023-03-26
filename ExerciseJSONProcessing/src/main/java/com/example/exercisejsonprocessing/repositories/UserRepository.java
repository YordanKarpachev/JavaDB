package com.example.exercisejsonprocessing.repositories;

import com.example.exercisejsonprocessing.entities.user.User;
import com.example.exercisejsonprocessing.entities.user.UserWithSoldItemsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from  users u join u.sellingItems i where i.buyer is not  null ")
    List<User> getAllUserWithSoldItems();
}
