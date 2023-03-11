package com.example.exercisespringdataintro.repositories;

import com.example.exercisespringdataintro.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findByReleaseDateAfter(LocalDate date);
}
