package com.example.xml_ex.repositories;


import com.example.xml_ex.entities.user.ExportUsersWithSoldItems;
import com.example.xml_ex.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select u from users  u where (select  count (p) from products p where p.seller = u and p.buyer is not null ) > 1")
    List<User> getAllWIthSoldItemsAndBuyerIsNotNull();
}
