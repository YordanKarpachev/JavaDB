package com.example.exercisejsonprocessing.repositories;

import com.example.exercisejsonprocessing.entities.products.Product;
import com.example.exercisejsonprocessing.entities.products.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Query(value = "SELECT p.name ,  p.price, concat(s.first_name, s.last_name) as full_name rom products as p join users s where s.id = p.seller_id and p.price > 500 and p.price < 1000 and p.buyer_id is null order by p.price;)
    @Query ("SELECT new com.example.exercisejsonprocessing.entities.products.ProductDTO" +
            " (p.name ,  p.price, p.seller.firstName, p.seller.lastName) " +
            " from products  as p " +
            " where p.price > :start and p.price < :end \n" +
            " and p.buyer is null\n" +
            " order by p.price" )

    List<ProductDTO> getAllProductWhichHaveNoBuyerAndPriceBetween(BigDecimal start, BigDecimal end);
}
